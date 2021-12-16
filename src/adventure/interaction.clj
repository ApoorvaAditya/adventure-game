(ns adventure.interaction
    (:require [clojure.string :as str])
    (:require [clojure.core.match :refer [match]])
    (:use adventure.utils))

(defn move [state dir]
    (let [current-location (get-current-location state)
          dest (dir (get-in state [:map current-location :dir]))]
        (if (nil? dest)
            (respond state "Cannot move in that direction")
            (assoc-in state [:adventurer :location] dest))))

(defn react [state input]
    (if (char? input)
        (update state :command str input)
        (match input
            [:move dir] (move state dir)
            "n" (move state :north)
            "s" (move state :south)
            "e" (move state :east)
            "w" (move state :west)
            :else (respond state "Cannot recognize input"))))

(defn canonicalize [input]
    (mapv keyword (str/split (-> input str/trim str/lower-case) #" ")))