
(ns everypredfns)

(require '[clojure.string :as str])

;; Clojure every-pred series

;; a new function is created
(every-pred string? str/blank?)

;; because every argument is a string and it’s blank
((every-pred string? clojure.string/blank?) " " "     " "\t")

;; one of the passed argument is "sample text" which isnt blank
((every-pred string? clojure.string/blank?) " " "     " "sample text")

;; we can use apply function to invoke result of every-pred an a list or a vector:
(apply (every-pred string? clojure.string/blank?) '(" " "     " "            "))

;; some-fn is a sibling function to every-pred

;; argument 15 meets condition #(> % 10) so the whole expression returns true.
((some-fn #(< % 5) #(> % 10)) 6 15 7 8)

;; every argument is bigger then 5 and smaller then 10 so the form returns false.
((some-fn #(< % 5) #(> % 10)) 6 15 7 8)

