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

;; flatten
;; Useful snippet: "merge" two or more vectors with `(comp vec flatten vector)`
(let [a [{:a "hi"} {:b "hey"}]
      b [{:c "yo"} {:d "hiya"}]
      c {:e ["hola" "bonjour"]}]
  ((comp vec flatten vector) a b c))

;; To only flatten one level, you can use (mapcat identity coll)
(flatten [[[1]] [[2 3]]])

(mapcat identity [[[1]] [[2 3]]])

;;juxt
(into {} (map (juxt identity (concat "@" name))  [:a :b :c :d]))


;; using HOFs
(def pairs [[:one 1] [:two 2] [:three 3] [:rest 4] [:rest 5] [:rest 6]])

(apply merge-with
       (comp vec flatten)
       (map (partial apply hash-map)
            pairs))



