(ns amortization-price.financial
  (:require [clojure.string :as str]))

(defn exp [x n]
  (reduce * 1 (repeat n x)))

(defn rate [x i]
  (-> (* x i)
      (/ 100)))

(defn tabela-price [PV i n]
  "Tabela Price returns payment"
	(->> (-> (rate 1 i)
           (+ 1)
           (exp n)) 
       (/ 1)
       (- 1)
       (/ (rate PV i))))