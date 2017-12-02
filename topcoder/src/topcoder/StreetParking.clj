(ns streetparking
  (:require [clojure.string :as str]))

(require '[clojure.string :as str])


;; a: street, D: driveway, B: bus stop, S: side street, -: other
;; each char describes nexy 5 meters
;; in front of <=> same index , obj i before obj j if i < j, obj i after obj j if i > j

;; return total number of possible parking spaces

;; 5 meters = '-'

;; parking rules: (P: parking)
;; 1. It is not directly in front of a private driveway. -> index(P) != index(D)
;; 2.	It is not directly in front of a bus stop. -> index(P) != index(B)
;; 3.	It is not 5 meters before a bus stop. -> index(P) != index(B) - 1
;; 4.	It is not 10 meters before a bus stop. -> index(P) != index(B) - 2
;; 5.	It is not directly in front of a side-street. -> index(p) != index(S)
;; 6.	It is not 5 meters before a side-street. -> index(p) != index(S) - 1
;; 7.	It is not 5 meters after a side-street. -> index(p) != index(S) + 1

;; Examples:
;; ---B--S-D--S--
;; P   P    P   P  -> 4 

(defn freeParks
  "return total number of possible parking spaces"
  [st]
  (letfn [(c1 [x] (not= (str/index-of st x) (= x "D")))
          (c2 [x] (not= (str/index-of st x) (= x "B")))
          (c3 [x] (not= (str/index-of st x) (- (str/index-of st "B") 1)))
          (c4 [x] (not= (str/index-of st x) (- (str/index-of st "B") 2)))
          (c5 [x] (not= (str/index-of st x) (= x "S")))
          (c6 [x] (not= (str/index-of st x) (- (str/index-of st "S") 1)))
          (c7 [x] (not= (str/index-of st x) (+ (str/index-of st "S") 1)))
          ]
  (count (filter true? (map (every-pred c1 c2 c3 c4 c5 c6 c7) st)))))

(freeParks "---B--S-D--S--")
(freeParks "DDBDDBDDBDD")
(freeParks "--S--S--S--S--")
(freeParks "SSD-B---BD-DDSB-----S-S--------S-B----BSB-S--B-S-D")
