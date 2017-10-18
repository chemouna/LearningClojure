
(ns turretdefense)

(defn turretDefense
  [xs ys ts]
  )

(defn abs [n] (max n (- n)))

(defn diff-with-itself
  [coll]
  (map abs (map - (rest coll) coll)))

(def xs '(3 5 6))
(def ys '(7 5 6))
(diff-with-itself xs)
(def diffX (diff-with-itself xs))
(def diffY (diff-with-itself ys))
;; (def timeNeed (map + diffX diffY))
