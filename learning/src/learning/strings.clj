(ns learning.strings)

(defn count-substring [txt sub]
  (count (re-seq (re-pattern sub) txt)))

(count-substring "the three truths" "th")
(count-substring "ababababab" "abab")

(defn count-substring1 [txt sub]
  (/ (- (count txt) (count (.replaceAll txt sub "")))
     (count sub)))

(count-substring1 "the three truths" "th")
(count-substring1 "ababababab" "abab")




