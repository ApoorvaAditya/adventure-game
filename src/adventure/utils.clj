(ns adventure.utils
    (:use adventure.constants))

(defn get-current-location [state]
    (get-in state [:adventurer :location]))

(defn get-current-room [state]
    (get-in state [:map (get-current-location state)]))

(defn remove-last-char [text]
    (if (> (count text) 0)
        (subs text 0 (- (count text) 1))
        text))

(defn clear-response [state]
    (assoc state :response ""))

(defn clear-command [state]
    (assoc state :command ""))

(defn respond [state res]
    (assoc state :response res))

(defn add-to-inventory [state item]
    (assoc-in state [:adventurer :inventory] (conj (get-in state [:adventurer :inventory]) item)))

(defn remove-from-inventory [state item]
    (assoc-in state [:adventurer :inventory] (disj (get-in state [:adventurer :inventory]) item)))

(defn add-to-room [state room item]
    (assoc-in state [:map room :contents] (conj (get-in state [:map room :contents]) item)))

(defn remove-from-room [state room item]
    (assoc-in state [:map room :contents] (disj (get-in state [:map room :contents]) item)))

(defn has-enemies [state]
    (not (empty? (get-in state [:map (get-current-location state) :enemies]))))

(defn create-projectile [state x y dir-x dir-y]
    (assoc-in state [:projectiles (java.util.UUID/randomUUID)] {:x x 
        :y y
        :vel projectile-vel
        :dir-x dir-x
        :dir-y dir-y}))

(defn calc-dir-x [state mouse-x mouse-y]
    (let [adventurer-x (get-in state [:adventurer :x])
          adventurer-y (get-in state [:adventurer :y])
          dir1-x (- mouse-x adventurer-x)
          dir1-y (- mouse-y adventurer-y)
          len (Math/sqrt (+ (* dir1-x dir1-x) (* dir1-y dir1-y)))
          dir-x (/ dir1-x len)
          dir-y (/ dir1-y len)]
          dir-x))

(defn calc-dir-y [state mouse-x mouse-y]
    (let [adventurer-x (get-in state [:adventurer :x])
            adventurer-y (get-in state [:adventurer :y])
            dir1-x (- mouse-x adventurer-x)
            dir1-y (- mouse-y adventurer-y)
            len (Math/sqrt (+ (* dir1-x dir1-x) (* dir1-y dir1-y)))
            dir-x (/ dir1-x len)
            dir-y (/ dir1-y len)]
            dir-y))

(defn calc-dist [x1 y1 x2 y2]
    (Math/sqrt (+ (* (- x2 x1) (- x2 x1)) (* (- y2 y1) (- y2 y1)))))