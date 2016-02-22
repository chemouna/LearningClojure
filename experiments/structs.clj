(ns experiments.structs
  (:require [clojure.core :as core]))

(defstruct book :name :category)

(struct book "The Joy Of Clojure" "Programming")

(type book)

(def bookMoreInfo (core/struct-map book :author "fogus"))

bookMoreInfo

