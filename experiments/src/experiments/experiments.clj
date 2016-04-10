(ns experiments.experiments)


(reduce conj #{} [:a :b :c])


;(reduce #(assoc %1 %2 (inc (%1 %2 0)))
;        {}
;        (re-seq #"\w+" s))


;; Calculate primes until 1000

(reduce
 (fn [primes number]
   (if (some zero? (map (partial mod number) primes))
     primes
     (conj primes number)))
 [2]
 (take 1000 (iterate inc 3)))


;; Get digits of a number

(defn explode-to-digits [number]
  (map #(Character/digit % 10) (str number)))

(explode-to-digits 56083)


(defn explode-to-digits2 [number]
  (map #(Character/getNumericValue %) (str number)))

(explode-to-digits2 56083)

(defn to-digit
  "Create a seq of digits from a number."
  ^{:user/comment "For Euler Problems (Specifically 16)"}
  [i & {:keys [type] :or {type int}}]
  (let [ss (str i)] (map type (map (fn [s] (- (int s) 48)) ss))))

(to-digit 56083)

(defn digits [n]
  (map #(Integer/parseInt (str %))  (seq (str n))))

(digits 56083)


(defn num->digits
  [num]
  (loop [n num res []]
    (if (zero? n)
      res
      (recur (long (/ n 10)) (cons (mod n 10) res)))))

(num->digits 56083)

;; comp examples
(def countif (comp count filter))
(countif even? [2 3 1 5 4])
(countif odd? [2 3 1 5 4])

;; Get 2nd to last element from a list
((comp second reverse) '("a" 2 7 "b"))


