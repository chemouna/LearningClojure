(ns learning.list-comprehensions)

(for [x [0 1 2 3 4 5 6]
      :let [y (* x 3)]
      :when (even? y)] y)

(for [[x y] '([:a 1] [:b 2][:c 0]) :when (= y 0)] x)

(time (dorun (for [x (range 1000) y (range 10000) :when (> x y)] [x y])))


(defn prime? [n]
  (not-any? zero? (map #(rem n %) (range 2 n))))

(for [x (range 3 33 2) :when (prime? x)] x)

(for [x (range 3 17 2) :when (prime? x)
      y (range 3 17 2) :when (prime? y)]
  [x y])


(for [x [1 2 3]
      y [1 2 3]
      :while (<= x y)
      z [1 2 3]]
  [x y z])
