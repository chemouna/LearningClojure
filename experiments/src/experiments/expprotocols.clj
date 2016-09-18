(ns experiments.expprotocols)


;; marker protocols

;; TODO : learn how to do exples that illustrate the point like this:

(defprotocol P (hi [_]))
(defprotocol M)
(deftype T
    [a]
  M P
  (hi [_] "hi there"))

(satisfies? P (T. 1))
(satisfies? M (T. 1))
(hi (T. 1))

(defprotocol M2 "Marker for 2")
(extend-type T M2)
(satisfies? M2 (T. 1))

