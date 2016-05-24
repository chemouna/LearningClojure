(ns learning.core)

(defn -main
 []
 (println "Hello...")
)

;(defn isPalindrome? [s]
;  (= s (apply str (reverse s) )))

;(defn -main
;  [& args]
;  (println "Started isPalindrome Calculation...")
;  (print " : ")
;  (println (isPalindrome? 9994))
;  )


;; takes a list and a nb and adds it to each element 
(defn list+ [lst a]
  (map #(+ % a) lst))

(list+ '(1 2 3) 10)
