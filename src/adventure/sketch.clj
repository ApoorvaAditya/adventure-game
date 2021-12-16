(ns adventure.sketch
  (:require [quil.core :as q])
  (:require adventure.constants)
  (:refer adventure.constants)
  (:require adventure.initial) ;; Only for init-state to test
  (:refer adventure.initial))  ;; Only for init-state to test

(defn draw-wall-horizontal [y]
  (q/rect 0 y window-width wall-width))

(defn draw-wall-vertical [x]
  (q/rect x 0 wall-width window-height))

(defn draw-wall-horizontal-with-door [y]
  (let [wall-dist (/ (- window-width door-width) 2)]
    (q/rect 0 y wall-dist wall-width)
    (q/rect (+ wall-dist door-width) y wall-dist wall-width)))

(defn draw-wall-vertical-with-door [x]
  (let [wall-dist (/ (- window-height door-width) 2)]
    (q/rect x 0 wall-width wall-dist)
    (q/rect x (+ wall-dist door-width) wall-width wall-dist)))

(defn draw-room [state]
  (let [player-location (-> state :adventurer :location)
        current-room (-> state :rooms player-location)
        nearby-rooms (:dir current-room)]
    (if (get nearby-rooms :north)
      (draw-wall-horizontal-with-door 0)
      (draw-wall-horizontal 0))
    (if (get nearby-rooms :south)
      (draw-wall-horizontal-with-door (- window-height wall-width))
      (draw-wall-horizontal (- window-height wall-width)))
    (if (get nearby-rooms :east)
      (draw-wall-vertical-with-door (- window-width wall-width))
      (draw-wall-vertical (- window-width wall-width)))
    (if (get nearby-rooms :west)
      (draw-wall-vertical-with-door 0)
      (draw-wall-vertical 0))))

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