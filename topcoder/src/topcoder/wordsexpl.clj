
(ns topwords.core
 (require [clojure.java.io :as io]
          [clojure.string :as str]))

;; calculate the top words for a given body of text, along with a count of the number of times each word occurred.

(def stop-words #{"other" "still" "again" "where" "could" "there"
                  "their" "these" "those" "after" "while" "almost"
                  "before" "through" "every" "being" "never" 
                  "should" "might" "thing" "among" "which"
                  "would" "though" "about"})

(defn get-words [line]
  (re-seq #"\p{Alpha}+" line))

(defn min-length [word]
 "Check a word has size bigger than 4"
 (< 4 (count word)))

(defn ignore-words
  "Ignore words that contain stop-words"
  [word]
  (if-not (contains? stop-words word) word))

(defn word-frequencies [filename]
  (with-open [rdr (io/reader filename)]
     (let [lines (line-seq rdr)
           words (comp get-words str/lower-case)
           preds (every-pred min-length ignore-words)]
       (frequencies (filter preds (words lines))))))

(defn top-words
 ([freqs] (top-words 20 freqs))
 ([n,freqs] (take n (sort-by val > freqs))))

(defn format-output [vals]
 (doseq [[k v] vals]
  (println (str k ":" v))))

(defn start []
  (let [file "test.txt"
        freqs (word-frequencies file)]
  (println "unique filtered words: " (count freqs))
  (format-output (top-words freqs))))


(start)



