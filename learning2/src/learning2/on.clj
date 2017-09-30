(ns learning2.on)

(defn on
  [f g]
  (fn [x y]
    (g (f x) (f y)) ))

(defn anagram?
  [word other-word]
  (and (not= word other-word)
       ((on = sort) word other-word)))

(anagram? "t" "d")
