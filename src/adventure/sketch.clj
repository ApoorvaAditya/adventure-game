(ns adventure.sketch
    (:refer-clojure :exclude [update])
    (:require [quil.core :as q]
              [quil.middleware :as m])
    (:use adventure.constants
          adventure.room
          adventure.interaction
          adventure.initial
          adventure.text-field)) ;; Only for init-state to test

(defn setup []
    (q/background 200)
    init-state)

(defn update [state]
    state)

(defn key-pressed [state event]
    (react state (:key event)))

(defn key-typed [state event]
    (println event)
    state) 

(defn draw [state]
    (q/background background-color)
    (draw-room state)
    (draw-text-field state "Hello"))   ;; Change this to current state

(q/defsketch adventure
    :title "Adventure"
    :middleware [m/fun-mode]
    :setup setup
    :draw draw
    :key-pressed key-pressed
    :key-typed key-typed
    :size [window-width window-height]) 