(ns amortization-price.lang-test
  (:require [clojure.test :refer :all]
            [amortization-price.core :refer :all]))

(deftest a-test-true
  (testing "True."
    (is (= 1 1))))

(defn lazyseq [a b] 
	(lazy-seq (cons a (lazyseq (+ a b) b))))

(deftest create-a-lazy-list
  (testing "return a lazzylist."
  	(let [res (take 4 (lazyseq 2 1))]
  		(println res)
	    (is (= (count res) 4))
	    (is (= (first res) 2))
	  	(is (= (nth res 1) 3))
	    (is (= (last res) 5)))))