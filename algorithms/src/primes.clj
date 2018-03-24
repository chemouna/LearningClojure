
(ns primes
  (:use clojure.tools.trace))

(clojure.tools.trace/trace-ns 'primes)

;; divisor test
(defn divides? [div n]
  (= (mod n div) 0))

(defn find-divisor [n test]
  (cond (> (* test test) n) n
        (divides? test n) test
        :else (recur n (inc test))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn isPrime? [n]
  (= n (smallest-divisor n)))

