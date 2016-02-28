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




