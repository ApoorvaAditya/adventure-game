(ns adventure.utils)

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