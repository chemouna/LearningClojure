(ns learning.sequences)


(defn simplified-map
  [f coll]
  (lazy-seq (when-let [s (seq coll)]
              (cons (f (first s)) (map f (rest s))))))

;; example usage of lazy-seq
(defn ints-from
  [n]
  (cons n (lazy-seq (ints-from (inc n)))))

(take 10 (ints-from 10))

