(ns experiments.usatoms)

(defmacro futures
  [n & exprs]
  (vec (for [_ (range n)
             expr exprs] `(future ~expr))))

(defmacro wait-futures [& args]
  `(doseq [f# (futures ~@args)] @f#))

(defn demoAtom [xs]
  (wait-futures 1 (swap! xs (fn [v]
                            (Thread/sleep 250)
                            (println "trying 4")
                            (conj v 4)))
              (swap! xs (fn [v]
                          (Thread/sleep 500)
                          (println "trying 5")
                          (conj v 5)))))

(demoAtom(atom #{1 2 3}))
