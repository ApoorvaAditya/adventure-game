(ns adventure.initial)

(def init-map
    {:foyer {:desc "The walls are freshly painted but do not have any pictures.  You get the feeling it was just created for a game or something."
             :title "in the foyer"
             :dir {:south :grue-pen}
             :contents #{:raw-egg}}
     :grue-pen {:desc "It is very dark.  You are about to be eaten by a grue."
                :title "in the grue pen"
                :dir {:north :foyer}
                :contents #{}}
     })

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