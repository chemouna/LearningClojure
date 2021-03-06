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

;; other examples

((every-pred number? odd?) 3 9 11)


(defn lower-case? [w] (= w (.toLowerCase w)))

(defn palindrome? [w] (= (seq w) (reverse w)))

((every-pred string? lower-case? palindrome?) "racecar")


;; testing if is a file and has extension rq

(defn is-file?
  [file]
  (.isFile file))

(defn has-suffix?
  [suffix file]
  (string/ends-with? (.getName file) suffix))

(def query-file?
  "Predicate testing if file is named *.rq."
  (every-pred is-file? (partial has-suffix? ".rq")))
