(ns adventure.response
    (:require [quil.core :as q])
    (:use adventure.constants))

(defn draw-response [state]
    (let [x (+ (/ (+ window-width door-width) 2) text-field-padding)
          y (- (- window-height text-field-height) text-field-padding)
          width  (- (/ (- window-width door-width) 2) (* text-field-padding 2))]
        (q/fill 0)
        (q/text-align :left :top)
        (q/text-size response-text-size)
        (q/text (:response state) x y width text-field-height)))