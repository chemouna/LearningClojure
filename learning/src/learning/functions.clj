(ns learning.functions)

(defn foo [a b c]
  (* a b c))

(foo 1 2 3)

(defn bar [a b & [c]]
  (if c
    (* a b c)
    (* a b 100)))

(bar 2 2 3)
(bar 2 2)

;; providing default values
(defn baz [a b & {:keys [c d] :or {c 10 d 20}}] ;; c d here if are not provided will use default values specified in :or 
  (* a b c d))

(baz 2 3)
(baz 2 3 :c 8)
(baz 2 3 :d 2)
(baz 2 3 :c 2 :d 4)
(baz 2 3 1 1) ;; => result:1200 -> which means it takes the default value if we dont specify :c & :d before their values

;; using :as to get what the user has specified
(defn boo [a b & {:keys [c d] :or {c 10 d 20} :as all-specified}]
  (println all-specified)
  (* a b c d))

(boo 2 3)
(boo 2 3 :c 5)
(boo 2 3 :c 4 :d 5)
(boo 2 3 :d 4)

;; using different signatures/arities (can be a way to provide a default value too)
(defn fn-diff-arit
  ([a b]   (fn-diff-arit a b 1000))
  ([a b c] (* a b c)))

(fn-diff-arit 2 3)
(fn-diff-arit 2 3 4)

;; using destructuring
(defn keyworded-map [& {function :function sequence :sequence}]
  (map function sequence))

(keyworded-map :sequence [1 2 3] :function #(+ % 5))

;; shortening it with :keys
(defn keyworded-map2 [& {:keys [function sequence]}]
  (map function sequence))

(keyworded-map2 :function #(+ % 5) :sequence [1 2 3])

(assoc {} :test "hello" {:test2 "hi"})

(dissoc {:test1 "test1", :test2 "test2"} :test1)

;; reimplementing split-at (in #t way than clojure core) -> hopefully more efficient
;(defn slit-at
;  [n coll)
  ;; TODO handle case n is neg
;  (let loop )
;  )


;; map
(map #(vector (first %) (* 2 (second %))) {:a 1 :b 2 :c 3})

(map {2 "two" 3 "three"} [5 3 2])

(filter identity (map {2 "two" 3 "three"} [5 3 2]))


;; doSeq

