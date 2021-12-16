(ns adventure.utils)

(defn get-current-location [state]
    (-> state :adventurer :location))