(ns algorithms.maxsubarray
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defn kadane
  "Return sum of the contiguous subsequence that has the largest sum."
  [coll]
  (let [maxes {:so_far 0 :ending_here 0}
        f     (fn [maxes x]
                (let [ending_here (max 0 (+ (maxes :ending_here) x))
                      so_far (max (maxes :so_far) ending_here)]
                  {:ending_here ending_here :so_far so_far}))]
    ((reduce f maxes coll) :so_far)))

(defn kadane2
  "Return sum of the contiguous subsequence that has the largest sum."
  [coll]

  )

;; (max_subseq_sum '(-2 1 -3 4 -1 2 1 -5 4))

;; test check
;; property that the max returned is >= then all elements of the passed in list

(def large-property (prop/for-all
                             [l (gen/not-empty  (gen/list gen/int))]
                             (>= (kadane l) (reduce max l))))
quickcheck_macros = "0.2"
quickcheck_macros = "0.2"


