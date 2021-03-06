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

;; juxt
(into {} (map (juxt identity (concat "@" name))  [:a :b :c :d]))

;; tree-seq
(tree-seq seq? identity '((1 2 (3)) (4)))

(tree-seq map? #(interleave (keys %) (vals %)) {:a 1 :b {:c 3 :d 4 :e {:f 6 :g 7}}})

;; interleave
(defn lazy-interleave [v1 v2]
  (when (and (seq v1) (seq v2))
    (lazy-cat [(first v1) (first v2)]
              (lazy-interleave (rest v1) (rest v2)))))

;; hash-map
(map #(hash-map % 0) (seq "abcdefgh"))

(apply hash-map (.split "a 1 b 2 c 3" " "))

;;
(def csv1 [["01/01/2012" 1 2 3 4]["06/15/2012" 38 24 101]])

(map #(hash-map (keyword (first %1)) (vec (rest %1))) csv1)

;; merge the list of maps into a single map
(apply merge '({"01/01/2012" [1 2 3 4]} {"06/15/2012" [38 24 101]}))

(let [val 42] (apply hash-map [[:key val] [:key val]]))

(apply hash-map (apply concat {:a 1, :b 2}))

(into {} (map #(apply hash-map %) [[:foo 1] [:bar 2]]))

(map-indexed #(hash-map (str %1) %2) [:foo :bar :baz])

(apply hash-map (mapcat #(.split % "=") (.split "foo=1&bar=2" "&")))

(->> [[:a 1] [:b 2]] (apply concat) (apply hash-map))

(let [x (transient (hash-map))]
  (dotimes [n 100] (assoc! x n n))
  (persistent! x))

(apply merge-with + (map #(hash-map % 1) [:a :a :b :c :d]))

(apply merge (map (fn [[k v]] (hash-map k (+ v 3))) {:k 3, :k2 4}))

;;
(reduce (fn [result {:keys [name employer]}] (update-in result [employer] conj name)) {}
        [{:name "jay fields", :current-city "new york", :employer "drw.com"}
         {:name "john dydo", :current-city "new york", :employer "drw.com"}
         {:name "mike ward", :current-city "chicago", :employer "drw.com"}
         {:name "chris george", :current-city "new york", :employer "thoughtworks.com"}])

;; Using HOFs
(def pairs [[:one 1] [:two 2] [:three 3] [:rest 4] [:rest 5] [:rest 6]])

(apply merge-with
       (comp vec flatten vector)
       (map (partial apply hash-map)
            pairs))


;; clojure.set/join





