(ns explore)

;; returning a pair
(defn quot-rem [m n]
  [(quot m n) (rem m n)])

(defn print-qr [m n]
  (let [[q r] (quot-rem m n)]
    (println q)
    (println r)))

;; or with a map
(defn quot-rem2 [m n]
  {:q (quot m n)
   :r (rem m n)})

(defn print-qr2 [m n]
  (let [{:keys [q r]} (quot-rem2 m n)]
    (println q)
    (println r)))

;; using let to define two variables where one depends on the other
(defn dependent-let [a]
  (let [x a y (+ 1 x)] y))

;; using recur
(defn compute-across [f coll x]
  (if (empty? coll)
    x
    (recur f (rest coll) (f x (first coll)))))

(defn total-of [coll]
  (compute-across + coll 0))

(total-of (range 1 5))

;; using iterate
(def powers-of-two
  (iterate (partial * 2) 1))

(nth powers-of-two 10)



