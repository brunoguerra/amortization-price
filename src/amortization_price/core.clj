(ns amortization-price.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defroutes main-routes
  (GET "/" [] "All good. Start hacking")  
  (GET "/auth" [] {:status 401 :body "Not authorized b"})
  (route/resources "/")
  (route/not-found "Not found"))

(def app
  (handler/site main-routes))



