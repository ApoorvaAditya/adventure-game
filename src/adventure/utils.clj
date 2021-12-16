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