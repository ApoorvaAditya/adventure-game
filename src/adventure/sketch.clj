(ns adventure.sketch
    (:refer-clojure :exclude [update take drop])
    (:require [quil.core :as q]
              [quil.middleware :as m])
    (:use adventure.constants
          adventure.room
          adventure.interaction
          adventure.initial ;; Only for init-state to test
          adventure.text-field
          adventure.utils
          adventure.response
          adventure.inventory))

(defn setup []
    (q/background 200)
    init-state)

(defn update [state]
    state)

(defn key-pressed [state event]
    (cond (= (:key-code event) 10)
             (clear-command (react state (canonicalize (:command state))))
          (= (:key-code event) 8)
             (clear-response (clojure.core/update state :command remove-last-char))
          (q/key-coded? (:raw-key event))
             (clear-response state)
          :else (clear-response (react state (:raw-key event)))))

(defn draw [state]
    (q/background background-color)
    (draw-room state)
    (draw-text-field state)
    (draw-response state)
    (if (= (:inventory state) :opened)
        (draw-inventory state)))

(q/defsketch adventure
    :title "Adventure"
    :middleware [m/fun-mode]
    :setup setup
    :draw draw
    :key-pressed key-pressed
    :size [window-width window-height]) 