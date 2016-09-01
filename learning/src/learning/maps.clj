(ns learning.maps)

;; mapcat

(mapcat reverse [[1 2 3][4 5 6]])

(mapcat (fn [[k v]]
          (for [[k2 v2] v]
            (concat [k k2] v2)))
        '{:a {:x (1 2) :y (3 4)}
          :b {:x (5 6) :y (7 8)}})

