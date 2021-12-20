(ns adventure.core
    (:gen-class)
    (:refer-clojure :exclude [update take drop])
    (:use adventure.initial 
          adventure.sketch 
          adventure.interaction))

(defn -main
    "Initialize the adventure"
    [& args])
