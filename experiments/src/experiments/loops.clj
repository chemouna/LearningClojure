(ns experiments.loops)

(take-while #(< % 150) (iterate #(* 2 %) 1))
