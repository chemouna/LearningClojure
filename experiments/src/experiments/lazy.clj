(ns experiments.lazy)

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))

(take 4 (positive-numbers 4))

;; multiple ways to generate fibonnacci sequence
;; with lazy-seq
(defn fib-seq-seq
  ([] (fib-seq-seq 1 1))
  ([a b] (lazy-seq (cons a (fib-seq-seq b (+ a b))))))

;; with lazy-cat
(def fib-seq-cat
  (lazy-cat [0 1] (map + (rest fib-seq-cat) fib-seq-cat)))

(take 5 fib-seq-cat)

;; with iterate
(def fib-seq-iterate
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))


