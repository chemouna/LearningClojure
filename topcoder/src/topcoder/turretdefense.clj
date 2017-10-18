
(ns turretdefense)

(defn turret-defense
  [xs ys ts]
  (let [diffX (diff-with-itself xs)
        diffY (diff-with-itself ys)
        timeNeed (map + diffX diffY)
        tn (cons 0 (map + timeNeed (butlast ts)))
        l (map - ts tn)]
    (first (keep-indexed #(if(neg? %2) %1) l))))


(defn abs [n] (max n (- n)))

(defn diff-with-itself
  [coll]
  (map abs (map - (rest coll) coll)))

(turret-defense '(3 5 6) '(7 5 6) '(11 15 16))

(turretDefense '(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16) '(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16) '(2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 31))
