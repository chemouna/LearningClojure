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
  (count (filter true? (map-indexed
                        #(and (not= %2 \D)
                              (not= %2 \B)
                              (not= (nth st (min (- (count st) 1) (+ %1 1))) \B)
                              (not= (nth st (min (- (count st) 1) (+ %1 2))) \B)
                              (not= %2 \S)
                              (not= (nth st (min (- (count st) 1) (+ %1 1))) \S)
                              (not= (nth st (max 0 (- %1 1))) \S))
                        (seq st)))))

(freeParks "---B--S-D--S--")
(freeParks "DDBDDBDDBDD")
(freeParks "--S--S--S--S--")
(freeParks "SSD-B---BD-DDSB-----S-S--------S-B----BSB-S--B-S-D")

