(ns adventure.core
    (:gen-class)
    (:refer-clojure :exclude [update take drop])
    (:use adventure.initial 
          adventure.sketch 
          adventure.interaction))

(defn -main
    "Initialize the adventure"
    [& args])
    ;; (loop [state init-state]
    ;;     (let [_  (println "What do you want to do")
    ;;         command (read-line)]
    ;;     (recur (react state (canonicalize command))))))
