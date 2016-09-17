(ns learning.exploremacros)

(defmacro try-catch-default
  [default-value operation]
  `(try
     ~operation
     (catch Exception x#
       (println "Error occured: " x#  ", defaults to: " ~default-value)
            ~default-value)))

(macroexpand-1 `(try-catch-default 10 (/ 1 4)))

(try-catch-default 10 (/ 1 4))

(defn load-default-value
  []
  (println "loading default value")
  (comment loading from db)
  3)

(macroexpand-1 '(try-catch-default (load-default-value) (/ 1 0)))

(try-catch-default (load-default-value) (/ 1 0))

;; load-default-value called twice
;; let's prevent that

(defmacro try-catch-default2
  [default-value operation]
  `(try
     ~operation
     (catch Exception x#
       (let [default# ~load-default-value]
         (println "Error occured : " x# ", defaults to : " default#)
         default#)
       )))

(macroexpand-1 '(try-catch-default2 (load-default-value) (/ 1 0)))

(try-catch-default2 (load-default-value) (/ 1 0))

;; now let's make it accomodate for code block operations
(defmacro try-catch-default3
  [default-value & operations]
  `(try
     ~@operations
     catch Exception x#
     (let [default# ~default-value]
       (println "Error occured : " x# ", defaults to : " default#)
       default#)
     ))

(macroexpand-1
 '(try-catch-default3 (load-default-value)
              (println "first op")
              (println "second op")
              (/ 1 0)))


