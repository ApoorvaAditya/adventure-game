(ns adventure.initial)

(def init-map
    {:foyer {:desc "The walls are freshly painted but do not have any pictures.  You get the feeling it was just created for a game or something."
             :title "in the foyer"
             :dir {:north :room1}
             :enemies #{}
             :contents #{:raw-egg
                         :dog-shit}}
     :room1 {:desc ""
             :title ""
             :dir {:south :foyer
                   :east :room2}
             :enemies {{:type :normal1}
                        {:type :normal1}}
             :contents #{:key}
             }
     :room2 {:desc ""
             :title ""
             :dir {:west :room1}
             :enemies {{:type :normal1}
                        {:type :normal1}}
             :contents #{:key}
             }
     :grue-pen {:desc "It is very dark.  You are about to be eaten by a grue."
                :title "in the grue pen"
                :dir {:north :foyer}
                :contents #{}}
     })

(def enemies
    {:normal1 {:color :red}
     :normal2 {:color :yellow}})

(def init-items
    {:raw-egg {:desc "This is a raw egg.  You probably want to cook it before eating it."
            :name "a raw egg" }})

(def init-adventurer
    {:location :foyer
        :inventory #{}
        :hp 10
        :lives 3
        :tick 0
        :seen #{}})

(def init-state 
    {:map init-map
     :items init-items
     :adventurer init-adventurer
     :command ""
     :response ""
     :inventory :opened})