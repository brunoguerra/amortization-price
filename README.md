## amortization-price

**lein 2.x compatible only**
* * * 

**amortization-price** is a clojure application server to respond TABELA-PRICE requests.

Here's what's included:

* [compojure](https://github.com/weavejester/compojure/) - A concise routing DSL for Ring/Clojure
* [midje](https://github.com/marick/Midje/) - My favourite clojure testing framework
* [ring-mock](https://github.com/weavejester/ring-mock) - Create ring request mocks for your unit tests
* [lazytest](https://github.com/stuartsierra/lazytest) - Watches your test files and reloads the ones you touch


### Getting Started

We're using [leiningen](https://github.com/technomancy/leiningen/) for project management so the first thing you will want to do is download all the dependencies:


`lein deps`


Then start the web server:


`lein ring server`


You should be ready to go.

### Tests

We're using two tests strategies for development with unit and acceptance tests found at test/amortization_price and test/amortization_price/test respectible
`ENVIRONMENT=test lein midje`


Make sure you have the `lein-midje` plugin installed in order to run your facts and get a better/prettier output:


`lein plugin install lein-midje 1.0.9`

### Included facts

In midje, tests are usually referred to as `facts`. One can be found in `core.clj` under the `test` directory.
