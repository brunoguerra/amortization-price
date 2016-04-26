(ns amortization-price.financial-test
  (:require [clojure.test :refer :all]
            [amortization-price.financial :refer :all]
            [clojure.math.numeric-tower :as math]))

(deftest a-exp-func
  (testing "with positive integers"
    (is (= 4 (exp 2 2)))
    (is (= 9 (exp 3 2))))
  (testing "with negative integers"
    (is (= 4 (exp -2 2)))
    (is (= 9 (exp -3 2)))
    (is (= -27 (exp -3 3)))))

(deftest a-rate-func
  (testing "with positive integers"
    (is (= 3 (rate 100 3)))
    (is (= 12 (rate 200 6)))))

(defn safe-money [f]
  (math/round f))

(deftest a-Tabela-Price
  (testing "with four installments"
    (let [pymt (tabela-price  100000 3 4)]
      (is (= (safe-money pymt) 26903)))
    (let [pymt (tabela-price  10000000 3 4)]
      (is (= (safe-money pymt) 2690270))))
  (testing "with long installments"
    (let [pymt (tabela-price  100000 3 120)]
      (is (= (safe-money pymt) 3089)))))