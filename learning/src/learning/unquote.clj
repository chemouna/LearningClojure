(ns learning.unquote)

`(1 3 ~(list 2 3))

`(1 3 ~@(list 2 3))


(def extra-values [1 2 3 4])
`(+ 100 200 ~@extra-values)
(+ 100 200 ~@extra-values) ;; doesn't work in an unquoted context
;; so to use it in unquoted context -> use apply
(apply + `(100 200 ~@extra-values))
