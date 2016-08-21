(ns learning.core)

;; takes a list and a nb and adds it to each element 
(defn list+ [lst a]
  (map #(+ % a) lst))


(defn split-with-size
  [[v & vs] s]
  (if-not v
    (list s)
    (cons (take v s) (split-with-size vs (drop v s)))))

(split-with-size [1 3 2 2] '(a b c d e f g h l m))


;; update-in
(def m {:a 1 :b 1 :c 2 :d 3})
(update-in m [:a] inc)


