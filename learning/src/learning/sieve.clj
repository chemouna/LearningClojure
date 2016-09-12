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

(time (primes-to 60))


;; Solution #2 using list comprehensions
(defn primes-to2
  "Returns a lazy sequence of prime numbers less than n"
  [n]
  (let [refs (boolean-array (+ n 1) true)
        root (int (math/sqrt n))]
    (do (doseq [i (range 2 n)
                :while (<= i n)
                :when  (aget refs i)]
          (doseq [j (range (* i i) n i)]
            (aset refs j false)))
        (filter #(aget refs %) (range 2 n)))))

(time (primes-to2 60))
x
