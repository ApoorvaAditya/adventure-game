(ns adventure.inventory
    (:require [quil.core :as q])
    (:use adventure.constants))

(defn draw-horizontal-line [y]
    (let [max-width (- (- window-width wall-width) inventory-padding)
          max-height (- (- window-height wall-width) inventory-padding)]
    (if (< y max-height)
        (do (q/line (+ wall-width inventory-padding) y max-width y)
            (draw-horizontal-line (+ y inventory-item-size))))))

(defn draw-vertical-line [x]
    (let [max-width (- (- window-width wall-width) inventory-padding)
          max-height (- (- window-height wall-width) inventory-padding)]
    (if (< x max-width)
        (do (q/line x (+ wall-width inventory-padding) x max-height)
            (draw-vertical-line (+ x inventory-item-size))))))

(defn draw-grid []
        (q/fill 255)
        (q/stroke-weight 2)
        (q/stroke 255)
        (draw-horizontal-line (+ wall-width inventory-padding))
        (draw-vertical-line (+ wall-width inventory-padding)))

(defn draw-background-rect []
    (let [max-width (- (- window-width wall-width) inventory-padding)
          max-height (- (- window-height wall-width) inventory-padding)]
        (q/fill 0)
        (q/rect-mode :corners)
        (q/rect (+ wall-width inventory-padding) (+ wall-width inventory-padding) max-width max-height)
        (q/rect-mode :corner)))

(defn draw-inventory [state]
    (draw-background-rect)
    (draw-grid))
