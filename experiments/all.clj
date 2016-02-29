(ns experiments.all)

;;  split a list into 2 sublists - elements that passed a predicate and elements that failed a predicate. 
;; using group-by and destructuring 
(let [{ evens true odds false} (group-by even? [1 2 3 4 5 6 7])]
  [evens odds])

;; this works but so there's no guarantee on the ordering of the vals that are returned.
(vals (group-by even? [1 2 3 4 5 6 7]))


;; using juxt 
;; juxtaposition : the fact of two things being seen or placed close together with contrasting effect.

((juxt filter remove) even? [1 2 3 4 5 6 7])


;; exploring juxt
((juxt :a :d) {:a 1 :b 2 :c 3 :d 4})

((juxt second count) "Clojure Is Awesome")

((juxt take drop) 3 [1 2 3 4 5 6])


(defn juxt-partition
  "Takes a predicate function, a collection and one ore more
   (fn predicate coll) functions that will be applied to the given collection.
   Example: (juxt-partition odd? [1 2 3 4] filter remove) => [(1 3) (2 4)]."
  [pred coll & fns]
  ((apply juxt (map #(partial % pred) fns)) coll))

(juxt-partition even? [1 2 3 4 5 6] filter remove)

((juxt inc dec) 2)

;; Using higher order functions
(def pairs [[:one 1] [:two 2] [:three 3]])
(into {} pairs)

(def pairs2 [[:one 1] [:two 2] [:three 3] [:rest 4] [:rest 5] [:rest 6]])
(into {} pairs2)

(apply merge-with
       (comp vec flatten vector)
       (map (partial apply hash-map)
             pairs))

;; auto curry macro
(defmacro defc
  [identifier bindings & body]
  (let [n# (count bindings)]
    `(def ~identifier
       (fn [& args#]
         (if (< (count args#) ~n#)
           (apply partial ~identifier args#)
           (let [myfn# (fn ~bindings ~@body)]
             (apply myfn# args#)))))))

(defc product-four-numbers
  [n1 n2 n3 n4]
  (* n1 n2 n3 n4))

((product-four-numbers 2) 3)

((product-four-numbers 2) 3 4)

((product-four-numbers 2) 3 4 5)


;; apply a list of functions to an argument, generating a list of the results
((juxt inc dec (partial * 3)) 4)

;;
(take 15 (cycle [1 2 3 4]))


;; recursion
(def factorial
  (fn [n]
    (loop [cnt n acc 1]
      (if (zero? cnt)
        acc
        (recur (dec cnt) (* acc cnt))
        ))))

(println (factorial 5))


(defn generateFibs
  []
  (loop [res [0 1]]
    (if (>= (count res) 20)
      res
      (recur (conj res (+' (inc (last res)) (dec (last (butlast res)))))))))

(println (generateFibs))

(conj [1 2] 3)
