(ns experiments.ctexplore
  (:require [uncomplicate.fluokitten.core :as flc :refer :all]))

;; (curry +)
(just 2)

(fmap (partial + 3) (just 2))

(fmap (partial + 3) nil)

(def posts {1 {:title "Apples"}})

(defn find-post [post-id])
(defn find-post [post-id]
  (if-let [post (posts post-id)]
    (just post)
    nil))

(defn get-post-title [post]
  (post :title))

(fmap get-post-title (find-post 1))

(fmap get-post-title (find-post 2))

;; functions are functors too
(def foo (fmap (partial + 3) (partial + 2)))
(foo 5)

;; Applicative
(def add3 (partial + 3))
(just add3)

(fapply (just add3) (just 2))

(<*> (just add3) (just 2)) 

(<*> [(partial * 2) (partial + 3)] [1 2 3])

(fmap * (just 5) (just 3))

;; Monads
(defn half [x]
  (if (even? x)
    (just (quot x 2))
    nil))

(half 4)
(half 5)

(half (just 5))

(bind (just 3) half)
(>>= (just 3) half)
(>>= (just 4) half)
(>>= nil half)

(>>= (just 20) half half half)
(>>= (just 20) half half)


