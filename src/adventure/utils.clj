(ns adventure.utils)

(defn get-current-location [state]
    (get-in state [:adventurer :location]))

(defn get-current-room [state]
    (get-in state [:map (get-current-location state)]))

(defn remove-last-char [text]
    (subs text 0 (- (count text) 1)))