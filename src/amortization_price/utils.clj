(ns amortization-price.utils
  (:use cheshire.core))

(defn parseBody [req]
  (if-let [body (:body req)]
      (let [body-string (slurp body)]
        (parse-string body-string))))

(defn to-json [res]
  (generate-string res { :pretty true }))

(defn parse-json [res]
  (parse-string res))