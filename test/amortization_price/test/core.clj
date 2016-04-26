(ns amortization-price.test.core
  (:use [amortization-price.core])
  (:use midje.sweet
        ring.mock.request))

(fact "Root route is successful"
      (:status (amortization-price.core/app
                (request :get "/"))) => 200)

