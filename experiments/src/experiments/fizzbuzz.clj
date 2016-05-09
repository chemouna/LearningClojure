(ns experiments.fizzbuzz
  (require '[clojure.core.match :refer [match]]))


(defn fizzbuzz [start finish] 
  (map (fn [n]
         (cond
           (zero? (mod n 15)) "FizzBuzz"
           (zero? (mod n 3)) "Fizz"
           (zero? (mod n 5)) "Buzz"
           :else n))
       (range start finish)))

(fizzbuzz 1 100)

;; fizzbuzz sol #2
(def fizzbuzz2 [start finish]
  (take finish (map #(let [s (str %2 %3) ] (if (seq s) s (inc %)) )
               (iterate inc start)
               (cycle [ "" "" "Fizz" ])
               (cycle [ "" "" "" "" "Buzz" ]))))

;; fizzbuzz sol #3 (with core match)
(def fizzbuzz3 [start finish]
  (doseq [n (range start finish)]
    (println (match [(mod n 3) (mod n 5)]
                    [0 0] "FizzBuzz"
                    [0 _] "Fizz"
                    [_ 0] "Buzz"
                    :else n))))

;; fizzbuzz sol #4 (similar to #2)
(defn fizzbuzz4 [start finish]
  (let [fizzes (cycle ["" "" "fizz"])
        buzzes (cycle ["" "" "" "" "buzz"])
        pattern (map str fizzes buzzes)
        numbers (iterate inc start)
        fizzbuzz (map #(if (.isEmpty %1) %2 %1) pattern numbers)]
    (doseq [m (take finish fizzbuzz)]
      (println m))))

