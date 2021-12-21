(ns adventure.initial
    (:use adventure.constants))

(def enemies
    {:normal1 {:color [0 0 255] 
                :diameter 20}
        :normal2 {:color [0 255 0]
                :diameter 20}})

(def init-map
    {:foyer {:desc "The walls are freshly painted but do not have any pictures.  You get the feeling it was just created for a game or something."
             :title "in the foyer"
             :dir {:north :room1}
             :enemies {}
             :contents #{:raw-egg
                         :stick}}
     :room1 {:desc ""
             :title ""
             :dir {:south :foyer
                   :east :room2}
             :enemies {1 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{:key}}
                    2 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}
             }
     :room2 {:desc ""
             :title ""
             :dir {:west :room1}
             :enemies {}
             :contents #{:key :chest}}})

(def init-items
    {:raw-egg {:desc "This is a raw egg.  You probably want to cook it before eating it."
               :name "a raw egg" 
               :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\raw-egg.png"
               :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
               :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}
     :key {:desc "This is a key. Might unlock something"
           :name "a key"
           :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\key.png"
           :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
           :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}
     :stick {:desc "This is a stick. Don't hit yourself with it"
             :name "a stick"
             :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\stick.png"
             :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
             :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}
     :chest {:desc "This is a locked chest. What treasures might it contain?"
             :name "a chest"
             :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\chest.png"
             :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
             :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}
     :treasure {:desc "This is a pile of gold. Enjoy!"
             :name "a pile of gold"
             :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\treasure.png"
             :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
             :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}})

(def init-adventurer
    {:location :foyer
        :prev-location :foyer
        :inventory #{}
        :x (/ window-width 2)
        :y (/ window-height 2)
        :vel-x 0
        :vel-y 0})

(def init-state 
    {:map init-map
     :items init-items
     :enemies enemies
     :images {}
     :adventurer init-adventurer
     :command ""
     :response ""
     :inventory :closed
     :combat-status false
     :projectiles {}})