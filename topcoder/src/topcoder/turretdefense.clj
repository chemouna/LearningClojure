
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
(def ts '(11 15 16))
(diff-with-itself xs)
(def diffX (diff-with-itself xs))
(def diffY (diff-with-itself ys))
(def timeNeed (map + diffX diffY))
(def tn (cons 0 (map + timeNeed (butlast ts))))

(defn first-index
  ([pred coll] (first-index coll pred 0))
  ([pred coll idx]
   (cond (= coll '()) -1
         (pred (first coll)) idx
         :else (recur pred (rest coll) (inc idx)))))

(def l (map - '(11 15 16) '(0 15 17)))

(defn is-neg? [x] (< x 0))

(first-index is-neg? l)

;;(first-index #((first %1) > (first (rest %1))) l)
