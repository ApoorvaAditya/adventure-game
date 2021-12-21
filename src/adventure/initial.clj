(ns adventure.initial
    (:use adventure.constants))

(def enemies
    {:normal1 {:color [0 0 255] 
               :diameter 40}
     :normal2 {:color [255 127 255]
               :diameter 20}
     :normal3 {:color [127 255 255]
               :diameter 10}
     :boss {:color [255 255 0]
               :diameter 5}})

(def init-map
    {:foyer {:desc "The walls are freshly painted but do not have any pictures.  You get the feeling it was just created for a game or something."
             :title "in the foyer"
             :dir {:north :room1}
             :enemies {}
             :contents #{}}
     :room1 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:south :foyer
                   :east :room2
                   :west :room3}
             :enemies {:1 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{:stick}}
                    :2 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}}
     :room2 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:west :room1
                   :south :room5}
             :enemies {:1 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{:raw-egg}}}
             :contents #{}}
     :room3 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:east :room1
                   :south :room4}
             :enemies {:1 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}}
     :room4 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:east :room9
                   :north :room3
                   :south :room6}
             :enemies {:1 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}}
     :room5 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:north :room2
                   :west :room9
                   :south :room8}
             :enemies {:1 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal1
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}}
     :room6 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:north :room4
                   :east :room7}
             :enemies {:1 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{:lighter}}}
             :contents #{}}
     :room7 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:south :final
                   :east :room8
                   :west :room6
                   :north :room9}
             :enemies {:1 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :3 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}}
    :room8 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:west :room7
                   :north :room5}
             :enemies {:1 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}
                    :2 {:type :normal2
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 4))
                        :vel-y (+ 1 (rand-int 4))
                        :contents #{}}}
             :contents #{}}
     :room9 {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:south :room7
                   :east :room5
                   :west :room4}
             :enemies {:1 {:type :boss
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 10))
                        :vel-y (+ 1 (rand-int 10))
                        :contents #{:key}}}
             :contents #{}}
     :final {:desc "Just a decrepit room"
             :title "in a decrepit room"
             :dir {:north :room7}
             :enemies {:1 {:type :boss
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 10))
                        :vel-y (+ 1 (rand-int 10))
                        :contents #{}}
                    :2 {:type :boss
                        :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 (get-in enemies [:normal1 :diameter])))))
                        :vel-x (+ 1 (rand-int 10))
                        :vel-y (+ 1 (rand-int 10))
                        :contents #{}}}
             :contents #{:chest}}
})

(def init-items
    {:raw-egg {:desc "This is a raw egg.  You probably want to cook it before eating it."
               :name "a raw egg" 
               :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\raw-egg.png"
               :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
               :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}
     :boiled-egg {:desc "This is a boiled egg.  Certainly tastes better than a raw one."
               :name "a boiled egg" 
               :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\boiled-egg.png"
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
     :lighter {:desc "This is a lighter. Don't burn yourself"
             :name "a lighter"
             :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\lighter.png"
             :x (+ wall-width (rand-int (- (- window-width (* 2 wall-width)) (* 2 item-size))))
             :y (+ wall-width (rand-int (- (- window-height (* 2 wall-width)) (* 2 item-size))))}
     :torch {:desc "This is a torch. Can light the way or cook something"
             :name "a torch"
             :url "C:\\Users\\apoor\\Programming\\Classes\\CS 225\\Honors\\adventure\\src\\adventure\\images\\torch.png"
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