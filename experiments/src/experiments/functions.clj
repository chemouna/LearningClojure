(ns experiments.functions)

(apply str ["str1", "str2", "str3"])

(max [1 2 3])

(apply max [1 2 3])

(filter even? [1 2 3 4])

(apply + `(1 2))

;; mapcat

(defn name-column [spec]
  (let [{:keys [user-name arg]} spec]
    (mapcat #(map (partial name-of user-name arg) %)
            ((juxt :short-names :long-names) spec))))


(defn name-col-width [name-cols]
  (reduce max (mapcat #(map count %) name-cols)))


;; juxt


;; partial


;; identity


;; disj
(disj {:k1 1 :k2 2 :k3 3} :k2)


;; shuffle


;; repeatedly
(repeatedly 10 (fn [] [:div]))

;; iterate


