(ns mapindexed)

;; keep
(keep :a '({:a 1} {:b 3} {:a 8} {:z 7}))

(filter :a '({:a 1} {:b 3} {:a 8} {:z 7}))

(map :a '({:a 1} {:b 3} {:a 8} {:z 7}))

;; keep-indexed
;; keep-indexed is extended version of keep function.
;; The difference is that the function f that we pass
;; as a first argument must have such signature:
;; (fn [index item])

(keep-indexed
 (fn [index item]
   [index (:a item)])
   '({:a 1} {:b 3} {:a 8} {:z 7}))

;; we want to return only values of keyword :a in the map if the map
;; is on the even position (like: 0, 2, 4, 6 and so on) in the collection
;; and has this keyword.
(keep-indexed
 (fn [index item]
   (when (even? index)
     (:a item)))
   '({:a 1} {:a 5} {:b 3} {:a 8} {:a 12} {:z 7}))


;; Filter elements from a sequence based on indexes
(defn filter-by-index [coll idxs]
  (keep-indexed #(when ((set idxs) %1) %2)
                coll))

(filter-by-index '(a b c d e f g) '(0 2 3 4))
; => (a c d e)

;; Find the first index of an item in a vector
;; Finding the *first* index isn't the clojure way, instead find all
;; the indexes, lazily. Then if you want the first one, just call first.
(defn positions
  [pred coll]
  (keep-indexed (fn [idx x]
                  (when (pred x)
                    idx))
                coll))

(def v ["one" "two" "three" "two"])
(first (positions #{"two"} v))

;; or with any other predicate
(first (positions pos? [-1 0 99 100 101]))

;; drop the nth item in a collection 
(defn drop-nth [n coll]
  (keep-indexed #(if (not= %1 n) %2) coll))

(drop-nth 2 '(\A \B \C \D \E))

;; Join string set into a single string with positions prepended
;; ["one" "two" "three"] convert it to :
;; 1. one
;; 2. two
;; 3. three

(clojure.string/join "\n"
   (keep-indexed #(str (inc %1) ". " %2) ["one" "two" "three"]))


;; map-indexed

;; transforms a sequence of items to another sequence, by applying
;; function f to every item in the original collection. The function
;; f takes index as a first parameter and item as second

(map-indexed
  (fn [index item] [index item])
  '(:a :b :c :d :e :f))

;; we don’t need to use some kind of loop when we need to transform items
;; in collection and we need to know the position of item in the sequence

;; Enumerate over a sequence
(dorun (map-indexed println ["dog", "cat", "bird"]))

;; Find the index of the smallest member of a vector
(first (apply min-key second (map-indexed vector [1 2 4 0 5])))

;; selection sort
(defn smallest [xs]
  (->> xs (map-indexed vector) (sort-by second) first))

(defn selection-sort [s]
  (->> (iterate
        (fn [[acc xs]]
          (let [[i x] (smallest xs)
                [l r] (split-at i xs)]
            [(conj acc x) (concat l (rest r))]))
        [[] s])
       (drop-while (comp not-empty second))
       ffirst))
