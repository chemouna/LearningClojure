(ns experiments.spec
  (:require [clojure.spec :as s]))

(s/conform even? 1000)

(s/conform even? 999)

(s/valid? nil? nil)

(s/valid? string? "abc")

(s/def ::suit #{:club :diamond :heart :spade})

(s/conform ::suit :club)

(s/def ::big-even (s/and integer? even? #(> % 1000)))

(s/valid? ::big-even :foo)
(s/valid? ::big-even 10)
(s/valid? ::big-even 10000)

(s/def ::name-or-id (s/or ::name string?
                          ::id   integer?))
(s/valid? ::name-or-id "abc")
(s/valid? ::name-or-id 10)

(s/conform ::name-or-id "abc")
(s/conform ::name-or-id 10)

;; nil is not considered valid with string?
(s/valid? string? nil)
;; to include nil and accept it as valid
(s/valid? (s/string nilableclo?) nil)

(with-out-str
  (s/explain ::suit 42))

(s/explain-data ::big-even 5)

(s/explain-str ::name-or-id :foo)


