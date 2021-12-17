(ns adventure.inventory
    (:require [quil.core :as q])
    (:use adventure.constants))

(defn draw-horizontal-line [y max-width max-height]
    (if (<= y max-height)
        (do (q/line (+ wall-width inventory-padding) y max-width y)
            (draw-horizontal-line (+ y inventory-item-size) max-width max-height))))

(defn draw-vertical-line [x max-width max-height]
    (if (<= x max-width)
        (do (q/line x (+ wall-width inventory-padding) x max-height)
            (draw-vertical-line (+ x inventory-item-size) max-width max-height))))

(defn draw-grid [max-width max-height]
        (q/fill 255)
        (q/stroke-weight 2)
        (q/stroke 255)
        (draw-horizontal-line (+ wall-width inventory-padding) max-width max-height)
        (draw-vertical-line (+ wall-width inventory-padding) max-width max-height))

(defn draw-background-rect [max-width max-height]
        (q/fill 0)
        (q/rect-mode :corners)
        (q/rect (+ wall-width inventory-padding) (+ wall-width inventory-padding) max-width max-height)
        (q/rect-mode :corner))

(defn draw-inventory [state]
    (let [max-width (-> window-width (-  (* 2 wall-width)) (- (* 2 inventory-padding)) (/ inventory-item-size) (q/floor) (* inventory-item-size) (+ wall-width) (+ inventory-padding))
          max-height (-> window-height (-  (* 2 wall-width)) (- (* 2 inventory-padding)) (/ inventory-item-size) (q/floor) (* inventory-item-size) (+ wall-width) (+ inventory-padding))]
        (draw-background-rect max-width max-height)
        (draw-grid max-width max-height)))
