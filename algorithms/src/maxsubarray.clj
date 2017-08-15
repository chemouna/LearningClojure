(ns algorithms.maxsubarray)

(defn max_subseq_sum
  "Return sum of the contiguous subsequence that has the largest sum."
  [coll]
  (let [maxes {:so_far 0 :ending_here 0}
        f     (fn [maxes x]
                (let [ending_here (max 0 (+ (maxes :ending_here) x))
                      so_far (max (maxes :so_far) ending_here)]
                  {:ending_here ending_here :so_far so_far}))]
    ((reduce f maxes coll) :so_far)))

;; (max_subseq_sum '(-2 1 -3 4 -1 2 1 -5 4))
