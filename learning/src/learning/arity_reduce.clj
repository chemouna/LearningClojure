(ns learning.arity_reduce)

;; implementation that most will write
(defn my-max [x & more]
  (if more
    (let [y (first more)]
      (recur (if (> x y) x y) (next more)))
  x))

;; using induction (a lot clearer & more idiomatic)
(defn clj-max
  ([x] x)
  ([x y] (if (> x y) x y))
  ([x y & more] (reduce max (max x y) more)))


