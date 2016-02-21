(ns experiments.structs)

(defstruct book :name :category)

(struct book "The Joy Of Clojure" "Programming")

(type book)

(def bookMoreInfo (struct-map :author "fogus"))
