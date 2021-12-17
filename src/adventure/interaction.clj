(ns adventure.interaction
    (:refer-clojure :exclude [take drop])
    (:require [clojure.string :as str])
    (:require [clojure.core.match :refer [match]])
    (:require [quil.core :as q])
    (:use adventure.utils))

(defn move [state dir]
    (let [current-location (get-current-location state)
          dest (dir (get-in state [:map current-location :dir]))]
        (if (nil? dest)
            (respond state "Cannot move in that direction")
            (assoc-in state [:adventurer :location] dest))))

(defn create-items-str [items]
    (if (empty? items)
        "You see nothing"
        (if (= (count items) 1) 
            (str "You see " (name (first items)) ".")
            (str "You see " (name (first items)) ".\n" (create-items-str (rest items))))))

(defn look [state] 
    (let [current-location (get-current-location state)
          items (get-in state [:map current-location :contents])]
          (respond state (create-items-str items))))

(defn open-inventory [state]
    (if (= (:inventory state) :opened)
        (assoc state :inventory :closed)
        (assoc state :inventory :opened)))

(defn examine [state item]
    state)

(defn drop [state item]
    state)

(defn take [state item]
    state)

(defn quit []
    (q/exit))

(defn react [state input]
    (println input)
    (if (char? input)
        (update state :command str input)
        (match input
            [:move dir] (move state dir)
            [:go dir] (move state dir)
            [:n] (move state :north)
            [:north] (move state :north)
            [:s] (move state :south)
            [:south] (move state :south)
            [:e] (move state :east)
            [:east] (move state :east)
            [:w] (move state :west)
            [:west] (move state :west)

            [:look] (look state)
            
            [:i] (open-inventory state)
            [:inventory] (open-inventory state)
            [:open :inventory] (open-inventory state)
            [:open] (open-inventory state)
            [:o] (open-inventory state)

            [:examine item] (examine state item)
            [:drop item] (drop state item)
            [:take item] (take state item)

            [:quit] (quit)
            [:exit] (quit)
            :else (respond state "Cannot recognize input"))))

(defn canonicalize [input]
    (mapv keyword (str/split (-> input str/trim str/lower-case) #" ")))