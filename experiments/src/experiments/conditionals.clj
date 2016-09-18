(ns experiments.conditionals)

;; condp
(defn enteringNbrs
  []
  (print "Enter a number: ")
  (flush) ; stays in a buffer otherwise
  (let [line (read-line)
        value (try
                (Integer/parseInt line)
                (catch NumberFormatException e line))]
    (println
     (condp = value
       1 "one"
       2 "two"
       3 "three"
       (str "Unexpected value, \"" value \")))))

;; (enteringNbrs)

(condp some [1 2 3 4]
  #{9 7 0} :>> dec
  #{0 4 6} :>> inc
  #{5 2 3} :>> #(+ 3 %))

(condp (comp seq re-seq) "foo=bar"
  #"[+](\w+)" :>> #(vector (-> % first (nth 1) keyword) true)
  #"[-](\w+)" :>> #(vector (-> % first (nth 1) keyword) false)
  #"(\w+)=(\w+)" :>> #(let [x (first %)]
                        [(keyword (nth x 1)) (nth x 2)]))
