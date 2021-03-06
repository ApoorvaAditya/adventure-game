(ns adventure.interaction
    (:refer-clojure :exclude [take drop])
    (:require [clojure.string :as str])
    (:require [clojure.core.match :refer [match]])
    (:require [quil.core :as q])
    (:use adventure.utils)
    (:use adventure.inventory)
    (:use adventure.initial))

(defn move [state dir]
    (let [current-location (get-current-location state)
          prev-location (get-in state [:adventurer :prev-location])
          dest (dir (get-in state [:map current-location :dir]))]
        (cond (:combat-status state) 
                  (respond state "Cannot move to room while fighting")
            (and (not (empty? (get-in state [:map current-location :enemies]))) (not (= dest prev-location)))
                (respond state "Cannot move there without defeating enemies")
            (nil? dest)
                (respond state "Cannot move in that direction")
            :else (assoc-in (assoc-in state [:adventurer :prev-location] current-location) [:adventurer :location] dest))))

(defn create-items-str [state items]
    (if (empty? items)
        "You see nothing"
        (if (= (count items) 1)
            (str "You see " (get-in state [:items (first items) :name]) ".")
            (str "You see " (get-in state [:items (first items) :name]) ".\n" (create-items-str state (rest items))))))

(defn look [state] 
    (let [current-location (get-current-location state)
          items (get-in state [:map current-location :contents])]
          (respond state (create-items-str state items))))

(defn open-inventory [state]
    (if (= (:inventory state) :opened)
        (assoc state :inventory :closed)
        (assoc state :inventory :opened)))

(defn examine [state item]
    (let [full-item (get-in state [:items item])]
        (if (= (:inventory state) :opened)
            (if (nil? (get-in state [:adventurer :inventory item]))
                (respond state "You don't have that item in your inventory.")
                (respond (assoc state :image-to-draw item) (:desc full-item)))
            (respond state "Open inventory first"))))

(defn examine-room [state]
    (respond state (get-in state [:map (get-current-location state) :desc])))

(defn back [state]
    (if (contains? state :image-to-draw)
        (dissoc state :image-to-draw)
        (respond state "Nothing to unexamine")))

(defn take [state item]
    (let [location (get-current-location state)]
        (cond  (:combat-status state) 
                (respond state "Cannot take while fighting")
            (not (empty? (get-in state [:map location :enemies])))
                (respond state "Cannot take something without defeating enemies")
            (contains? (get-in state [:map location :contents]) item)
                (respond (remove-from-room (add-to-inventory state item) location item) (str "Picked up " (name item) "."))
            :else (respond state "That item doesn't exist in the room"))))

(defn drop [state item]
    (let [location (get-current-location state)]
        (if (contains? (get-in state [:adventurer :inventory]) item)
            (respond (add-to-room (remove-from-inventory state item) location item) (str "Dropped " (name item)))
            (respond state (str (name item) " doesn't exist in your inventory")))))

(defn fight [state]
    (if (not (empty? (get-in state [:map (get-current-location state) :enemies])))
        (assoc state :combat-status true)
        (respond state "Nothing to fight")))

(defn run [state]
    (if (not (:combat-status state))
        (respond state "Nothing to run from")
        (let [current-location (get-current-location state)
              dest (get-in state [:adventurer :prev-location])]
            (assoc-in (assoc state :combat-status false) [:adventurer :location] dest))))

(defn use-item [state item]
    (if (contains? (get-in state [:adventurer :inventory]) item)
        (cond (= item :key)
              (if (contains? (get-in state [:adventurer :inventory]) :chest)
                  (-> state
                      (assoc-in [:adventurer :inventory] (conj (get-in state [:adventurer :inventory]) :treasure))
                      (update-in [:adventurer :inventory] disj item)
                      (update-in [:adventurer :inventory] disj :chest)
                      (respond "You found the treasure! You win! Restart or quit"))
                  (respond state "Need a chest to open"))
             (= item :raw-egg)
                (-> state
                    (update-in [:adventurer :inventory] disj item)
                    (respond "You eat the raw egg. It tastes bad"))
            (= item :boiled-egg)
                (-> state
                    (update-in [:adventurer :inventory] disj item)
                    (respond "You eat the boiled egg. It tastes bad. At least it's better than eating raw."))
             (= item :stick)
                (-> state
                    (drop item)
                    (respond "You poke your eye while playing with the stick. You throw it away"))
             (= item :lighter)
                (if (contains? (get-in state [:adventurer :inventory]) :stick)
                    (-> state
                        (update-in [:adventurer :inventory] disj item)
                        (update-in [:adventurer :inventory] disj :stick)
                        (update-in [:adventurer :inventory] conj :torch))
                    (respond state "Needs something to light on fire."))
             (= item :torch)
                (if (contains? (get-in state [:adventurer :inventory]) :raw-egg)
                    (-> state 
                        (update-in [:adventurer :inventory] disj item)
                        (update-in [:adventurer :inventory] disj :raw-egg)
                        (update-in [:adventurer :inventory] conj :boiled-egg))
                    (respond state "Nothing to cook.")))
        (respond state "Cannot use this")))

(defn teleport [state dest]
    (if (not (nil? (get-in state [:map dest])))
        (assoc-in state [:adventurer :location] dest)
        (respond state "Cannot teleport there")))

(defn kill-all [state enemies]
    (if (not (empty? enemies))
        (let [id (key (first enemies))
              enemy (val (first enemies))]
            (-> state
                (update-in [:map (get-current-location state) :contents] clojure.set/union (:contents enemy))
                (update-in [:map (get-current-location state) :enemies] dissoc id)
                (kill-all (rest enemies))))
        state))

(defn restart [state]
    (assoc init-state :images (:images state)))

(defn quit []
    (q/exit)
    (System/exit 0))

(defn react [state input]
    (if (char? input)
        (update state :command str input)
        (match input
            [:move dir] (move state dir)
            [:go dir] (move state dir)
            [:n] (move state :north)
            [:north] (move state :north)
            [:up] (move state :north)
            [:s] (move state :south)
            [:south] (move state :south)
            [:down] (move state :south)
            [:e] (move state :east)
            [:east] (move state :east)
            [:right] (move state :east)
            [:w] (move state :west)
            [:west] (move state :west)
            [:left] (move state :west)

            [:look] (look state)
            
            [:i] (open-inventory state)
            [:inventory] (open-inventory state)
            [:open :inventory] (open-inventory state)
            [:open] (open-inventory state)
            [:o] (open-inventory state)

            [:examine] (examine-room state)
            [:examine :room] (examine-room state)
            [:examine item] (examine state item)
            [:back] (back state)
            [:take item] (take state item)
            [:pick :up item] (take state item)
            [:pickup item] (take state item)
            [:drop item] (drop state item)

            [:f] (fight state)
            [:fight] (fight state)
            [:run] (run state)

            [:use item] (use-item state item)

            [:teleport dest] (teleport state dest)
            [:tp dest] (teleport state dest)
            [:kill :all] (kill-all state (get-in state [:map (get-current-location state) :enemies]))

            [:restart] (restart state)
            [:r] (restart state)

            [:quit] (quit)
            [:exit] (quit)
            :else (respond state "Cannot recognize input"))))

(defn canonicalize [input]
    (mapv keyword (str/split (-> input str/trim str/lower-case) #" ")))