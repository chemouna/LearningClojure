(ns experiments.all2
  (:require [clojure.math.numeric-tower :as math]))

;(if-let (even? 2) "even")

(defn sum-even-numbers [nums]
  (if-let [nums (seq (filter even? nums))]
    (reduce + nums)
    "No even numbers found")
  )

(sum-even-numbers [1 2 3 4 5 6])

(defn easter
  [year]
  (let [a (rem year 19)
        b (math/floor (/ year 100))
        c (rem year 100)
        d (math/floor (/ b 4))
        e (rem b 4)
        f (math/floor (/ (+ b 8) 25))
        g (math/floor (/ (+ (- b f) 1) 3))
        h (rem (+ (- (- (+ (* 19 a) b) d) g) 15) 30)
        i (math/floor (/ c 4))
        k (rem c 4)
        L (rem (- (- (+ (+ 32 (* 2 e)) (* 2 i)) h) k) 7)
        m (math/floor (/ (+ (+ a (* 11 h)) (* 22 L)) 451))
        month (math/floor (/ (+ (- (+ h L) (* 7 m)) 114) 31))
        day (+ (rem (+ (- (+ h L) (* 7 m)) 114) 31) 1)]
    (println day "/" month "/" year)))

