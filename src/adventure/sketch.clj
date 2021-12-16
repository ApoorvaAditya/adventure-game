(ns adventure.sketch
  (:require [quil.core :as q])
  (:require adventure.constants)
  (:refer adventure.constants)
  (:require adventure.room)
  (:refer adventure.room)
  (:require adventure.initial) ;; Only for init-state to test
  (:refer adventure.initial))  ;; Only for init-state to test

(defn setup []
  (q/background 200))

(defn draw []
  (q/background 255 0 0)
  (q/no-stroke)
  (draw-room init-state))   ;; Change this to current state

(q/defsketch adventure
  :title "Adventure"
  :settings #(q/smooth 2)
  :setup setup
  :draw draw
  :size [window-width window-height])