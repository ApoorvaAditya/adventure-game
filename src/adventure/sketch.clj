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

(defn init-images [state]
    (assoc state :images 
         (apply merge (map #(hash-map % (q/load-image (get-in state [:items % :url]))) (keys (:items state))))))

(defn setup []
    (init-images init-state))

(defn draw-adventurer [state]
    (let [curr-fill (q/current-fill)
          adventurer (:adventurer state)]
        (q/fill adventurer-color)
        (q/ellipse (:x adventurer) (:y adventurer) adventurer-radius adventurer-radius)))

(defn update-adventurer-position [state min-x max-x min-y max-y]
    (let [x (get-in state [:adventurer :x])
          y (get-in state [:adventurer :y])
          vel-x (get-in state [:adventurer :vel-x])
          vel-y (get-in state [:adventurer :vel-y])
          try-x (+ x vel-x)
          try-y (+ y vel-y)
          new-x (if (> try-x max-x)
                    max-x 
                    (if (< try-x min-x)
                        min-x
                        try-x))
          new-y (if (> try-y max-y)
                    max-y 
                    (if (< try-y min-y)
                        min-y
                        try-y))]
    (assoc-in (assoc-in state [:adventurer :x] new-x) [:adventurer :y] new-y)))

(defn update-enemies-positions [state]
    )

(defn update [state]
    (let [padding (+ wall-width (/ adventurer-radius 2))]
        (update-adventurer-position state padding
            (- window-width padding)
            padding
            (- window-height padding))))

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
        (draw-inventory state)
        (draw-adventurer state))
    (if (contains? state :image-to-draw)
        (draw-item state)))

(q/defsketch adventure
    :title "Adventure"
    :middleware [m/fun-mode]
    :setup setup
    :draw draw
    :update update
    :key-pressed key-pressed
    :size [window-width window-height]) 