(ns learning.arrays-manip)

(defn accum-array
  [acc-fn init size assoc-list]
  (let [arr (make-array (type init) size)
        accum (fn [[index a]]
                (aset arr index (acc-fn (aget arr index) a)))]
    (java.util.Arrays/fill arr init)
    (dorun (map accum assoc-list))
    (into [] arr)))



(defn accum-array-t
  [acc-fn init size assoc-list]
  (let [arr (transient (into [] (take size (repeat init))))
        accum (fn [[index a]]
                (assoc! arr index (acc-fn (nth arr index) a)))]
    (dorun (map accum assoc-list))
    (persistent! arr)))


(defn disjunct [a b] (or a b))

(accum-array-t
 disjunct false 10
 [[0 true] [8 true ] [9 true]])


(def zip (partial map vector))

(accum-array-t
 + 0 10
 (zip [0 1 4 1 3 6 3 9 3] (repeat 1)))


(defn minfree (comp search checklist))

(minfree [0 1 2 3 5 6 2])


;; comp
(def neg-quotient (comp - /))
(neg-quotient 8 3)

(def concat-and-reverse (comp (partial apply str) reverse str))
(concat-and-reverse "hello" "sir")

(map (comp - (partial + 3) (partial * 2)) [1 2 3 4])

(filter (comp not zero?) [0 1 0 2 0 3 0 4])

(def countif (comp count filter))
(countif even? [1 2 3 4 5 6 7])

((comp second reverse) '("a" 2 7 "b"))

(#((apply comp first (repeat %2 rest)) %1) [1 2 3 4 5 6] 3)


;; list*
(list 1 [2 3])
(list* 1 [2 3])

;; apply


;; partial


;; defstruct
(defstruct person :name :age :height)
(struct person "george" 22 115)
(:name person)
