(ns amortization-price.core
  (:use compojure.core
        amortization-price.financial)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [amortization-price.installments-ctrl :as installments]))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn logging-middleware [app]
  (fn [req]
    (println (str (:request-method req) " " (:uri req) " ?" (:query-string req)))
    (-> (assoc req :uuid (uuid))
        (app))))

(defroutes main-routes
  (POST "/installment" [] installments/create)
  (GET "/" [] #(str "Request uuid: " (% :uuid)))  
  (GET "/installment/:id" [id] installments/show)  
  (DELETE "/installment/:id" [id] installments/delete)  
  (route/resources "/")
  (route/not-found "Not found"))


(def app
  (handler/site (logging-middleware main-routes)))



