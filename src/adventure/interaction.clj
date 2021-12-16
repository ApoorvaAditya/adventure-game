(ns adventure.interaction
    (:require [clojure.string :as str])
    (:require [clojure.core.match :refer [match]])
    (:use adventure.utils))

(defn move [state dir]
    (let [current-location (get-current-location state)
          dest (dir (get-in state [:map current-location :dir]))]
        (if (nil? dest)
            (do (println "Cannot move in that direction")
                state)
            (assoc-in state [:adventurer :location] dest))))

(defn react [state input]
    (println input)
    (if (char? input)
        (update state :command str input)
        (match input
            [:move dir] (move state dir)
            "n" (move state :north)
            :up (move state :north)
            :down (move state :south)
            :right (move state :east)
            :left (move state :west)
            :else (do (println "Cannot recognize input")
                      state))))

(defn canonicalize [input]
    (mapv keyword (str/split (-> input str/trim str/lower-case) #" ")))