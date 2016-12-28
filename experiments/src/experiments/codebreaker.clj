(ns experiments.codebreaker
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]
            ;;[clojurechec.test.check :as ctest]
            ))

(defn exact-matches
  [secret guess]
  (count (filter true? (map = secret guess))))

(defn score [secret guess]
  {::exact-matches (exact-matches secret guess)
   ::loose-matches 0})

(def peg? #{:y :g :r :c :w :b})

(s/def ::code (s/coll-of peg? :min-count 4 :max-count 6))

(s/def ::exact-matches nat-int?)
(s/def ::loose-matches nat-int?)

(s/fdef score :args (s/and (s/cat :secret ::code :guess ::code)
                           (fn [{:keys [secret guess]}]
                             (= (count secret) (count guess))))
        :ret (s/keys :req [::exact-matches ::loose-matches])
        :fn (fn [{{secret :secret} :args ret :ret}]
              (<= 0 (apply + (vals ret)) (count secret))))

(s/exercise (:args (s/get-spec `score)))

(stest/check `score)

(s/exercise-fn `score)
