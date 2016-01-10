

((defn isPalindrome? [s]
  (= s (apply str (reverse s) ))))

  (println isPalindrome? 9999)
  (println isPalindrome 012901)
