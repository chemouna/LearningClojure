(ns experiments.idioms)

(defn empty-non-idiom
  [coll]
  (not (empty? coll)))

(defn empty-idiom
  [coll]
  (seq coll))


(defn flatten-level-idiom
  [coll]
  (mapcat list coll))

