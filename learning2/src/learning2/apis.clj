
(ns learning2.apis
  (:use [clojure.tools.trace :as trace]))

(trace/trace-ns 'learning2.apis)

(def acc1 (ref 100))
(def acc2 (ref 200))

(defn transfer-money [a1 a2 amount]
  (dosync
   (alter a1 - amount)
   (alter a2 + amount)
   amount))

;(println @acc1 @acc2)

;(transfer-money acc1 acc2 20)

;(println @acc1 @acc2)

(comment "
(loop [x 10]
  (when (> x 1)
    (println x)
    (recur (- x 2))))
")

;; lazy-cat
;(time (first (concat (sort > (range 10)) (sort > (range 1e7)))))

;(time (first (lazy-cat (sort > (range 10)) (sort > (range 1e7)))))

;; recur

(defn fact [x]
  (loop [cnt x acc 1]
    (if (= cnt 1)
      acc
      (recur (dec cnt) (* acc cnt)))))

;(fact 4)

; apply
;(apply concat '((1 2 3) (4 5)))

;; for
;(for [x [0 1 2 3 4 5]
;      :let [y (* x 3)]
;      :when (even? y)] y)

; (for [x [0 1 2 3 4] :when (not (even? x))] x)

;; letfn

;; letfn doesn't accept defining both a var and a function at the same time 
(comment "
(letfn [(x 2)
        (twice [y]
          (* x y))]
  (println (twice 4)))
")

;; this work though
(let [x 2]
  (letfn [(twice [y]
            (* x y))]
    (println (twice 4))))

;; for

;; dosync

;; doall


