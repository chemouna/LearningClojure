(ns experiments.codebreaker
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]
            ;;[clojurechec.test.check :as ctest]
            ))

(defn exact-matches
  [secret guess]
  (count (filter true? (map = secret guess))))

(defn all-matches [secret guess]
  (apply + (vals (merge-with min
                             (select-keys (frequencies secret) guess)
                             (select-keys (frequencies guess) secret)))))

(defn score [secret guess]
  (let [exact (exact-matches secret guess)
        all   (all-matches secret guess)]
  {::exact-matches (exact-matches secret guess)
   ::loose-matches (- all exact)}))

(def peg? #{:y :g :r :c :w :b})

(s/def ::code (s/coll-of peg? :min-count 4 :max-count 6))

(s/def ::exact-matches nat-int?)
(s/def ::loose-matches nat-int?)

(s/def ::secret-and-guess (s/and (s/cat :secret ::code :guess ::code)
                                 (fn [{:keys [secret guess]}]
                                   (= (count secret) (count guess)))))

(s/fdef score
        :args ::secret-and-guess
        :ret (s/keys :req [::exact-matches ::loose-matches])
        :fn (fn [{{secret :secret} :args ret :ret}]
              (<= 0 (apply + (vals ret)) (count secret))))

(s/fdef match-count
        :args ::secret-and-guess
        :ret nat-int?
        :fn (fn [{{secret :secret} :args ret :ret}]
              (<= 0 ret (count secret))))

(s/exercise (:args (s/get-spec `score)))

(stest/check `score)
(s/exercise-fn `score)

(s/exercise-fn `exact-matches 10 (s/get-spec `match-count))
(stest/check-fn exact-matches (s/get-spoec `match-count))

(stest/instrument `exact-matches)
(s/exercise-fn `score)
(stest/check `score)

(stest/summarize-results (stest/check `score))
