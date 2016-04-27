(ns amortization-price.financial
  (:require [clojure.string :as str]
            [clojure.math.numeric-tower :as math]))

(defn exp [x n]
  (reduce * 1 (repeat n x)))

(defn rate [x i]
  (-> (* x i)
      (/ 100)))

(defn tabela-price
  "Tabela Price returns payment"
  [present-value interest-rate n]
	(->> (-> (rate 1 interest-rate)
           (+ 1)
           (exp n)) 
       (/ 1)
       (- 1)
       (/ (rate present-value interest-rate))
       (math/round)))

(defn scur
  "Safe Currency - is a float with 2 descimals shifted as integer" 
  [f]
  (math/round (* f 100.0)))

(defn round [f] (math/round f))

(defn scur-to-float
  "Convert Safe Currency to "
  [n]
  (/ n 100.0))

(defn format-scur [n]
  (-> (->> (scur-to-float n)
           (format "%.2f"))
      (clojure.string/replace #"," ".")))