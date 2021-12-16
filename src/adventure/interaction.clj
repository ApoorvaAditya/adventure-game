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
            (do (println dest)
                (assoc-in state [:adventurer :location] dest)))))

(defn react [state input]
    (match input 
        [:move dir] (move state dir)
        :else "Cannot recognize input"))

(defn canonicalize [input]
    (mapv keyword (str/split (-> input str/trim str/lower-case) #" ")))