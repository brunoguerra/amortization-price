(ns amortization-price.test.core
  (:use [amortization-price.core])
  (:use midje.sweet
        ring.mock.request
        amortization-price.financial
        amortization-price.utils))

(fact "Root route is successful"
      (:status (amortization-price.core/app
                (request :get "/"))) => 200)

(def req-installment-params {
	"present_value" 1000 
	"monthly_interest_rate" 0.03 
	"number_of_installments" 4
})

(defn post-url [url payload]
	(amortization-price.core/app
             	 (body (request :post url) (to-json payload))))

(defn get-url [url]
	(amortization-price.core/app
             	 (request :get url)))

(defn delete-url [url]
	(amortization-price.core/app
             	 (request :delete url)))

(defn has-show [url]
	(= (:status (get-url url)) 200))

(defn can-delete [url]
	(= (:status (delete-url url)) 200))

(defn url-id [res]
	(str "/installment/" (get res "id")))

(fact "POST /installment"
  (let [res (post-url "/installment" req-installment-params)]
  	(:status res) => 200
  	(let [body (parse-json (:body res))]
  		(get body "installment_value") => "269.03"
  		(has-show (url-id body)) => true
  		(can-delete (url-id body)) => true
  		(has-show (url-id body)) => false)))
  	

