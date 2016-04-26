(defproject amortization-price/amortization-price "1.0.0-SNAPSHOT" 
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.0"]
                 [com.stuartsierra/lazytest "1.2.3"]
                 [org.clojure/tools.nrepl "0.2.0-beta9"]]
  :ring {:handler amortization-price.core/app}
  :profiles {:dev
             {:dependencies [[midje "1.4.0"] [ring-mock "0.1.2"] [org.clojure/tools.namespace "0.2.11"]]
              :plugins [[lein-midje "2.0.0-SNAPSHOT"]]}}
  :repositories {"stuart" "http://stuartsierra.com/maven2"}
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.7.1"]]
  :description "A Tabela-Price web application.")