
(ns learning2.apis)

(def acc1 (ref 100))
(def acc2 (ref 200))

(defn transfer-money [a1 a2 amount]
  (dosync
   (alter a1 - amount)
   (alter a2 + amount)
   amount))

(println @acc1 @acc2)

(transfer-money acc1 acc2 20)

(println @acc1 @acc2)

