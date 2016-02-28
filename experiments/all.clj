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



