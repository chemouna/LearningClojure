(ns learning2.on)

(defn on
  ([f g]
     (fn [x y]
       (f (g x)
          (g y))))
  ([f g & args]
     (on f #(apply g % args))))

(defn anagram?
  [word other-word]
  (and (not= word other-word)
       ((on = sort) word other-word)))

(anagram? "nagaram" "anagram")
