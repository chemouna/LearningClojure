(ns experiments.all2
  (:require [clojure.math.numeric-tower :as math]))

;(if-let (even? 2) "even")


(defn sum-even-numbers [nums]
  (if-let [nums (seq (filter even? nums))]
    (reduce + nums)
    "No even numbers found")
  )

(sum-even-numbers [1 2 3 4 5 6])
