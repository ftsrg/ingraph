(ns ingraph.sre.queries.routesensor-test
  (:require [clojure
             [pprint :as pprint]
             [test :refer :all]]
            [ingraph.sre.queries
             [routesensor-pattern :as rs]
             [utils :as utils]]
            [ingraph.sre.queries.utils.tb-loader :as tb-loader]
            [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte])
  (:import [ingraph.ire Indexer IngraphVertex]
           [scala.collection.immutable HashMap HashSet]
           scala.Tuple2))

(tufte/add-basic-println-handler! {})

(def rs-proto-1 (tufte/profile {} (utils/compile-pattern 'rs/p-1 [] 5)))

(comment
  "eval this to get the tasks -->" (pprint/pprint (utils/get-tasks rs-proto-1)))

(def rs-proto-2 (tufte/profile {} (utils/compile-pattern 'rs/p-2 [] 5)))

(comment
  "eval this to get the tasks -->" (pprint/pprint (utils/get-tasks rs-proto-2)))

(def tb-inject-1 (tb-loader/load 'inject-1))

(deftest test-routesensorpositive-tb-inject-1
  (testing "RouteSensorPositive query should return expected results for TrainBenchmark inject-1"
    (doseq [proto [`rs-proto-1 `rs-proto-2]]
      (let [inject-1 (tb-loader/load 'inject-1)
            results (tufte/profile {} (tufte/p (keyword (str 'tb-inject-1 '- (utils/short-name proto)))
                                               (into [] (pattern/run (-> proto resolve deref) [:route :follows :sw-p :target :sw :monitored-by :sensor] {:indexer inject-1}))))]
        (is (= 7 (count results)))))))
