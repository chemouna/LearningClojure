
(ns experiments.cljtime
  (:require [clj-time.core :as t]
            [clj-time.format :as tf]))

(t/date-time 1986 10 14)

(t/hour (t/date-time 1986 10 14 22))

(t/from-time-zone (t/date-time 1986 10 22) (t/time-zone-for-offset -2))

(t/after? (t/date-time 1986 10) (t/date-time 1986 9))

(t/plus (t/date-time 1986 10 14) (t/months 1) (t/weeks 3))

(t/today-at 12 00)

(def built-in-formatter (tf/formatters :basic-date-time))
(def custom-formatter (tf/formatter "yyyyMMdd"))

(tf/show-formatters)

(tf/parse custom-formatter "20100305")

(tf/unparse custom-formatter (t/date-time 2010 03 05))

