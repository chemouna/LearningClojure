(ns experiments.playingmaps
  (:require [clojure.core :as core]
             [clojure.repl :as doc]))

(def mymap {:fname "John" :lname "Doe"})

(def anothermap (assoc mymap :fname "Jack"))

(core/println anothermap)
(core/println mymap)


;; 

(def teams { "Man. United" {:points 1500 :rd 350} 
            "Man. City"   {:points 1450 :rd 300} })

(update-in teams ["Man. United" :points] inc)

(assoc-in teams ["Man. United" :points] 1)

(-> teams
    (assoc-in ["Man. City" :points] 300)
    (assoc-in ["Man. City" :rd] 100))

(dissoc teams "Man. United")

teams
