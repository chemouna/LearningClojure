(ns topcoder.iditarod
  (:require [clojure.string :as str]))

(defn avg-minutes
  [coll]
  )

;; {"12:00 PM, DAY 1","12:01 PM, DAY 1"}
;; 241

(str/split "12:00 PM, DAY 1" #":")
