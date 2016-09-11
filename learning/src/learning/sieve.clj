(ns learning.sieve
  (:require [clojure.math.numeric-tower :as math]))

(defn primes-to
  "calculate lazy sequence of prime numbers <= n using siev of eratosthens"
  [n]
  (let [root (long (math/sqrt n)),
        cmpsts (boolean-array (inc n)),
        cullp (fn [p]
                (loop [i (* p p)]
                  (if (<= i n)
                    (do (aset cmpsts i true)
                        (recur (+ i p))))))]
    (do (dorun (map #(cullp %) (filter #(not (aget cmpsts %))
                                       (range 2 (inc root)))))
        (filter #(not (aget cmpsts %)) (range 2 (inc n))))))

(primes-to 60)

