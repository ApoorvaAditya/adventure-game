(ns adventure.core
    (:gen-class)
    (:use adventure.initial 
          adventure.sketch 
          adventure.interaction))

(defn -main
    "Initialize the adventure"
    [& args]
    (loop [state init-state]
        (let [_  (println state)
            command (read-line)]
        (recur (react state (canonicalize command))))))
