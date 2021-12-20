(ns adventure.room
    (:require [quil.core :as q])
    (:use adventure.constants 
          adventure.utils))

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

(defn draw-items-in-room [state items]
    (if (not (empty? items))
        (let [item (get-in state [:items (first items)])
              image (get-in state [:images (first items)])]
            (q/image-mode :corner)
            (q/image image (:x item) (:y item) item-size item-size)
            (draw-items-in-room state (rest items)))))

(defn draw-room [state]
    (let [current-room (get-current-room state)
          nearby-rooms (:dir current-room)]
        (q/no-stroke)
        (q/fill wall-color)
        (if (and (get nearby-rooms :north) (not (:combat-status state)))
            (draw-wall-horizontal-with-door 0)
            (draw-wall-horizontal 0))
        (if (and (get nearby-rooms :south) (not (:combat-status state)))
            (draw-wall-horizontal-with-door (- window-height wall-width))
            (draw-wall-horizontal (- window-height wall-width)))
        (if (and (get nearby-rooms :east) (not (:combat-status state)))
            (draw-wall-vertical-with-door (- window-width wall-width))
            (draw-wall-vertical (- window-width wall-width)))
        (if (and (get nearby-rooms :west) (not (:combat-status state)))
            (draw-wall-vertical-with-door 0)
            (draw-wall-vertical 0))
        (draw-items-in-room state (:contents current-room))))