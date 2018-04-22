
(ns learning2.lists
  (:use clojure.test))

(defn zeros
  "Creates a list of n zeros"
  [n]
  (repeat n 0))

(defn insertAtFillGapsZeros
  [x i v]
  (cond
    (= (count v) (- i 1)) (conj v x)
    :else (recur x i (conj v 0))))

;; maybe its not efficient
;; how can we do it only with lists

(defn insertAtWithFillGaps
  [x i coll]
  (seq (insertAtFillGapsZeros x i (vec coll))))

(insertAtWithFillGaps 2 6 '(1 5))

