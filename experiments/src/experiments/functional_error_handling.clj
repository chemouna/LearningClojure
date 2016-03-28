(ns experiments.functional-error-handling)

(defn clean-address [params]
  "Ensure (params :address) is present"
  (if (empty? (params :address))
    [nil "Please enter your address"]
    [params nil]))

(defn clean-email [params]
  "Ensure (params :email) matches *@*.*"
  (if (and (params :email) (re-find #".*@.*\..*" (params :email)))
    [params nil]
    [nil "Please enter an email address"]))


(defn clean-phone [params]
  "Ensure phone number matches (555) 555-5555"
  (if (and (params :phone) (re-find #"\([0-9]{3}\) [0-9]{3}-[0-9]{4}" (params :phone)))
    [params nil]
    [nil "Please enter your phone number in (555) 555-5555 format."]))


(defn clean-state [params]
  "Ensure state is one of OR or WA. Cascadians unite!"
  (case (params :state)
    "WA" [params nil]
    "OR" [params nil]
    [nil "We only want people from Oregon or Washington, for some reason."]))

; And our examples
(defn clean-contact-bad [params]
  (let [[params err] (clean-email params)
        [params err] (if (nil? err) (clean-address params) [nil err])
        [params err] (if (nil? err) (clean-phone params) [nil err])
        [params err] (if (nil? err) (clean-state params) [nil err])]
    [params err]))

(defn apply-or-error [f [val err]]
  (if (nil? err)
    (f val)
    [nil err]))

(defn clean-contact-ok [params]
  (let [result (clean-email params)
        result (apply-or-error clean-address result)
        result (apply-or-error clean-phone result)
        result (apply-or-error clean-state result)]
    result))

(defn clean-contact-good [params]
  (->> (clean-email params)
       (apply-or-error clean-address)
       (apply-or-error clean-phone)
       (apply-or-error clean-state)))

(defmacro err->> [val & fns]
  (let [fns (for [f fns] `(apply-or-error ~f))]
    `(->> [~val nil]
          ~@fns)))

(defn clean-contact [params]
  (err->> params
          clean-email
          clean-address
          clean-phone
          clean-state))


(comment
(use 'clojure.test)

(is (= (clean-address {})
       [nil "Please enter your address"]))
(is (= (clean-address {:address "123 Fake St."})
       [{:address "123 Fake St."} nil]))

(is (= (clean-email {:email "test"})
       [nil "Please enter an email address"]))
(is (= (clean-email {})
       [nil "Please enter an email address"]))
(is (= (clean-email {:email "test@test.com"})
       [{:email "test@test.com"} nil]))

(is (= (clean-phone {})
       [nil "Please enter your phone number in (555) 555-5555 format."]))
(is (= (clean-phone {:phone "1-800-555-1234"})
       [nil "Please enter your phone number in (555) 555-5555 format."]))
(is (= (clean-phone {:phone "(800) 555-1234"})
       [{:phone "(800) 555-1234"} nil]))

(is (= (clean-state {:state "CA"})
       [nil "We only want people from Oregon or Washington, for some reason."]))
(is (= (clean-state {:state "WA"})
       [{:state "WA"} nil]))

(is (= (clean-contact {})
       [nil "Please enter an email address"])) 

(is (= (clean-contact {:email "test@test.com"})
       [nil "Please enter your address"])) 

(is (= (clean-contact {:email "test@test.com" :address "123 Fake St."})
       [nil "Please enter your phone number in (555) 555-5555 format."]))

(is (= (clean-contact {:email "test@test.com" :address "123 Fake St." :phone "(800) 555-1234"})
       [nil "We only want people from Oregon or Washington, for some reason."]))

(is (= (clean-contact {:email "test@test.com" :address "123 Fake St." :phone "(800) 555-1234"})
       [nil "We only want people from Oregon or Washington, for some reason."]))

(is (= (clean-contact {:email "test@test.com" :address "123 Fake St." :phone "(800) 555-1234" :state "WA"})
       [{:email "test@test.com" :address "123 Fake St." :phone "(800) 555-1234" :state "WA"} nil]))
)
