(ns adventure.text-field
    (:require [quil.core :as q])
    (:use adventure.constants))

(defn draw-text-field [state text]
    (let [y (- (- window-height text-field-height) text-field-padding)
          width  (- (/ (- window-width door-width) 2) (* text-field-padding 2))]
    (q/fill 0)
    (q/rect text-field-padding y width text-field-height)
    (q/fill 255)
    (q/text-align :left :top)
    (q/text-size 20)
    (q/text text text-field-padding y)))