
(ns learning2.explore-test
  (:use clojure.test))

(defn setup-test
  []
  (println "Setting up tests"))

(defn teardown-test
  []
  (println "Tear down of tests"))

(defn wrap-setup
  [f]
  (println "wrapping test")
  (setup-test)
  (f)
  (teardown-test))

;(use-fixtures :once wrap-setup)

(def table (atom {}))

(defn wrap-setup2
  [f]
  (swap! table assoc :a 2)
  (f))

(use-fixtures :once wrap-setup2)

(deftest test-with-table
  (is (= (get @table :a) 2)))

(run-tests)

