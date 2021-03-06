(ns adventure.text-field
    (:require [quil.core :as q])
    (:use adventure.constants))

(defn draw-text-field [state]
    (let [y (- (- window-height text-field-height) text-field-padding)
          width  (- (/ (- window-width door-width) 2) (* text-field-padding 2))]
    (q/fill 0)
    (q/rect text-field-padding y width text-field-height)
    (q/fill 255)
    (q/text-align :left :top)
    (q/text-size text-size)
    (q/text (:command state) text-field-padding y)))