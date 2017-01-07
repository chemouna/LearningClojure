(ns learning.core)

; recur
(def some_join [coll result] "Example of using recur to join strings"
  (if (= 1 (count coll)) (str result (first coll)))
  (recur (rest coll) (str result (first coll)) ", "))

; https://clojurebridge.github.io/community-docs/docs/clojure/recur/

(def a1 (double-array '(1.0 2.0 3.0 4.0)))

(def a2 '(int-array '(9 8 7 6)))



