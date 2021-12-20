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
        (q/ellipse (:x adventurer) (:y adventurer) adventurer-diameter adventurer-diameter)))

(defn update-adventurer-position [state]
    (let [padding (+ wall-width (/ adventurer-diameter 2))
          min-x padding
          max-x (- window-width padding)
          min-y padding
          max-y (- window-height padding)
          x (get-in state [:adventurer :x])
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
        (-> state 
            (assoc-in [:adventurer :x] new-x)
            (assoc-in [:adventurer :y] new-y))))

(defn draw-enemies [state enemies]
    (if (not (empty? enemies))
        (let [enemy (val (first enemies))
              type (get-in state [:enemies (:type enemy)])]
            (q/fill (:color type))
            (q/ellipse (:x enemy) (:y enemy) (:diameter type) (:diameter type))
            (draw-enemies state (rest enemies)))))

(defn update-enemies-positions [state enemies]
    (if (not (empty? enemies))
        (let [id (key (first enemies))
              enemy (val (first enemies))
              type (get-in state [:enemies (:type enemy)])
              padding (+ wall-width (/ (:diameter type) 2))
              min-x padding
              max-x (- window-width padding)
              min-y padding
              max-y (- window-height padding)
              x (:x enemy)
              y (:y enemy)
              vel-x (:vel-x enemy)
              vel-y (:vel-y enemy)
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
                            try-y))
             new-vel-x (if (or (> try-x max-x) (< try-x min-x))
                           (- vel-x)
                           vel-x)
             new-vel-y (if (or (> try-y max-y) (< try-y min-y))
                           (- vel-y)
                           vel-y)]
            (-> state
                (assoc-in [:map (get-current-location state) :enemies id :x] new-x)
                (assoc-in [:map (get-current-location state) :enemies id :y] new-y)
                (assoc-in [:map (get-current-location state) :enemies id :vel-x] new-vel-x)
                (assoc-in [:map (get-current-location state) :enemies id :vel-y] new-vel-y)
                (update-enemies-positions (rest enemies))))
        state))

(defn draw-projectiles [state projectiles]
    (if (not (empty? projectiles))
        (let [projectile (first projectiles)
            x (:x projectile)
            y (:y projectile)]
            (q/fill projectile-color)
            (q/rect-mode :center)
            (q/rect x y projectile-size projectile-size)
            (q/rect-mode :corner)
            (draw-projectiles state (rest projectiles)))))

(defn update [state]
    (-> state
        (update-adventurer-position)
        (update-enemies-positions (get-in state [:map (get-current-location state) :enemies]))))

(defn key-pressed [state event]
    (cond (= (:key-code event) 10)
             (clear-command (react state (canonicalize (:command state))))
          (= (:key-code event) 8)
             (clear-response (clojure.core/update state :command remove-last-char))
          (= (:key event) :up)
             (assoc-in state [:adventurer :vel-y] (- adventurer-vel))
          (= (:key event) :down)
             (assoc-in state [:adventurer :vel-y] adventurer-vel)
          (= (:key event) :left)
             (assoc-in state [:adventurer :vel-x] (- adventurer-vel))
          (= (:key event) :right)
             (assoc-in state [:adventurer :vel-x] adventurer-vel)
          (q/key-coded? (:raw-key event))
             (clear-response state)
          :else (clear-response (react state (:raw-key event)))))

(defn key-released [state event]
    (cond (= (:key event) :up)
              (assoc-in state [:adventurer :vel-y] 0)
          (= (:key event) :down)
              (assoc-in state [:adventurer :vel-y] 0)
          (= (:key event) :left)
              (assoc-in state [:adventurer :vel-x] 0)
          (= (:key event) :right)
              (assoc-in state [:adventurer :vel-x] 0)
          :else state))

(defn mouse-pressed [state event]
    (if (= (:button event) :left)
        (create-projectile state (/ window-width 2) (/ window-height 2) 0 1)))

(defn draw [state]
    (q/background background-color)
    (draw-room state)
    (draw-text-field state)
    (draw-response state)
    (draw-adventurer state)
    (draw-enemies state (get-in state [:map (get-current-location state) :enemies]))
    (draw-projectiles state (:projectiles state))
    (if (= (:inventory state) :opened)
        (draw-inventory state))
    (if (contains? state :image-to-draw)
        (draw-item state)))

(q/defsketch adventure
    :title "Adventure"
    :middleware [m/fun-mode]
    :setup setup
    :draw draw
    :update update
    :mouse-pressed mouse-pressed
    :key-pressed key-pressed
    :key-released key-released
    :size [window-width window-height]) 