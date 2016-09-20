(ns experiments.recursion)

;; example of using recur
(defn recursive-reverse
  [coll]
  (loop [coll coll
         acc (empty coll)]
    (if (empty? coll)
      acc
      (recur (rest coll) (cons (first coll) acc)))))

(recursive-reverse '(1 2 3 4))

