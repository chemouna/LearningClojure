(ns learning.arrays-manip)

(defn accum-array
  [acc-fn init size assoc-list]
  (let [arr (make-array (type init) size)
        accum (fn [[index a]]
                (aset arr index (acc-fn (aget arr index) a)))]
    (java.util.Arrays/fill arr init)
    (dorun (map accum assoc-list))
    (into [] arr)))


;; using transient

(defn accum-array-t
  [acc-fn init size assoc-list]
  (let [arr (transient (into [] (take size (repeat init))))
        accum (fn [[index a]]
                (assoc! arr index (acc-fn (nth arr index) a)))]
    (dorun (map accum assoc-list))
    (persistent! arr)))

;; (accum-array (+) 0 (1,2) [(1,2), (1,6)])


(defn disjunct [a b] (or a b))

(accum-array-t
 disjunct false 10
 [[0 true] [8 true ] [9 true]])


(def zip (partial map vector))

(accum-array-t
 + 0 10
 (zip [0 1 4 1 3 6 3 9 3] (repeat 1)))





