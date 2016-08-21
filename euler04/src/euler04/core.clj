(ns euler04.core
    (:require [clojure.math.numeric-tower :as math]
             [clojure.string :as s])
    (:gen-class))

  ;solution #1
(defn ispalindrome? [num]
 (let [num-str (str num)
       num-length (count num-str)
       num-slice-depth (int (/ num-length 2))
       str-left (subs num-str 0 num-slice-depth)
       str-right (subs num-str (- num-length num-slice-depth))]
   (= str-left (s/reverse str-right))))

(defn nth-digit-palindrome-list [num]
 (let [lower-num (math/expt 10 (dec num))
       upper-num (math/expt 10 num)
       num-range (range lower-num upper-num)
       mult-list (for [i num-range j num-range] (* i j))]
   (filter ispalindrome? mult-list)))

  ;solution #2
(def reverse-int [n]
  (reverse-int n 0)
  ([a b]
   (if (= a 0)
    b
    (recur (int (/a 10.0)) (+ (* b 10) (mod a 10))))))

  ; https://github.com/juliengrenier/clojure-euler/blob/master/src/euler/euler_04.clj
  ;https://github.com/zolrath/Project-Clojuler/blob/master/src/euler/04.clj
  
(defn -main
 [& args]
 (println "Solution 1 :")
 (println (apply max (nth-digit-palindrome-list 3))))
