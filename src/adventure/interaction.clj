(ns adventure.interaction
    (:require [clojure.string :as str])
    (:require '[clojure.core.match :refer [match]])
    (:require adventure.utils))

(defn move [state dir]
    (let [current-location (get-current-location state)
          dest (dir (get-in state [:map current-location :dir]))]
        (if (nil? dest)
            "Cannot move in that direction"
            (assoc-in state [:adventurer :location] dest))))