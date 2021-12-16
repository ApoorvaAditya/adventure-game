(ns adventure.interaction
    (:require [clojure.string :as str])
    (:require [clojure.core.match :refer [match]])
    (:use adventure.utils))

(defn move [state dir]
    (let [current-location (get-current-location state)
          dest (dir (get-in state [:map current-location :dir]))]
        (if (nil? dest)
            (assoc state :response "Cannot move in that direction")
            (assoc-in state [:adventurer :location] dest))))

(defn react [state input]
    (if (char? input)
        (update state :command str input)
        (match input
            [:move dir] (move state dir)
            :up (move state :north)
            "n" (move state :north)
            :down (move state :south)
            "s" (move state :south)
            :right (move state :east)
            "e" (move state :east)
            :left (move state :west)
            "w" (move state :west)
            :else (assoc state :response "Cannot recognize input"))))

(defn canonicalize [input]
    (mapv keyword (str/split (-> input str/trim str/lower-case) #" ")))