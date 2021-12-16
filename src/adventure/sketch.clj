(ns adventure.sketch
    (:refer-clojure :exclude [update])
    (:require [quil.core :as q]
              [quil.middleware :as m])
    (:use adventure.constants
          adventure.room
          adventure.interaction
          adventure.initial ;; Only for init-state to test
          adventure.text-field
          adventure.utils))

(defn setup []
    (q/background 200)
    init-state)

(defn update [state]
    state)

(defn key-pressed [state event]
    (cond (= (:key-code event) 10)
            (let [new-state (react state (canonicalize (:command state)))]
                (assoc new-state :command ""))
          (= (:key-code event) 8)
            (clojure.core/update state :command remove-last-char)
          :else (react state (:raw-key event))))

(defn draw [state]
    (q/background background-color)
    (draw-room state)
    (draw-text-field state))   ;; Change this to current state

(q/defsketch adventure
    :title "Adventure"
    :middleware [m/fun-mode]
    :setup setup
    :draw draw
    :key-pressed key-pressed
    :size [window-width window-height]) 