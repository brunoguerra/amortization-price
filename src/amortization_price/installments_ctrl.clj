(ns amortization-price.installments-ctrl
	(:require [amortization-price.financial :as fin]
            [amortization-price.utils :refer :all]
            [clojure.java.io :as io]
            [ring.util.response :refer [content-type]]))

(def DOCS-PATH "docs/")

(defn file-name-from [uuid]
  (str DOCS-PATH uuid ".json"))

(defn doc-file [id]
  (io/as-file (file-name-from id)))

(let [file (io/file DOCS-PATH)] 
  (if (not (.exists file))
    (.mkdirs file)))

(defn calc-tabela-price [{ 
  present-value "present_value" 
  interest-rate "monthly_interest_rate"
  n-inst "number_of_installments" }]
  (fin/tabela-price
    (fin/scur present-value)
    (* interest-rate 100)
    (int n-inst)))

(defn save-request [res body req]
  (->> (select-keys body ["present_value" "monthly_interest_rate" "number_of_installments"])
       (conj res)
       (to-json)       
       (spit (file-name-from (:uuid req))))
  res)

(defn create 
  "Service to create installment json file"
  [req]
  (let [body (parseBody req)]
    (let [pmt-value (calc-tabela-price body)]
      (-> { :id (:uuid req) 
            :installment_value (fin/format-scur pmt-value) }
          (save-request body req)
          (to-json)))))

(defn file-not-found [file]
  { :status 404 :body (str "Not found! " (.getName file)) })

(defn file-read [file]
  (if (.exists file)
      (slurp file)
      (file-not-found file)))

(defn show [{{id :id} :route-params}]
  (let [file (doc-file id)]
    (file-read file)))

(defn delete [{{id :id} :params}]
  (let [file (doc-file id)]
    (if (.exists file)
      (do (.delete file) {:status 200 :body "calc has been removed!" })
      (file-not-found file))))