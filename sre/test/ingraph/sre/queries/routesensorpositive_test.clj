(ns ingraph.sre.queries.routesensorpositive-test
  (:require [clojure
             [pprint :as pprint]
             [test :refer :all]]
            [ingraph.sre.queries
             [routesensorpositive-pattern :as rsp]
             [utils :as utils]]
            [ingraph.sre.queries.utils.tb-loader :as tb-loader]
            [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte])
  (:import [ingraph.ire Indexer IngraphVertex]
           [scala.collection.immutable HashMap HashSet]
           scala.Tuple2))

(tufte/add-basic-println-handler! {})

(defn compile-pattern [def-sym k]
  (let [def @(resolve def-sym)]
    (tufte/profile {} (tufte/p (-> def-sym str keyword) (pattern/compile (:pattern def) (:config def) {:k k})))))


(def rsp-proto-1 (utils/compile-pattern 'rsp/p-1 5))

(comment
  "eval this to get the tasks -->" (pprint/pprint (utils/get-tasks rsp-proto-1)))

(def rsp-proto-2 (utils/compile-pattern 'rsp/p-2 5))

(comment
  "eval this to get the tasks -->" (pprint/pprint (utils/get-tasks rsp-proto-2)))


(def tb-inject-1 (tb-loader/load 'inject-1))

(deftest test-routesensorpositive-tb-inject-1
  (testing "RouteSensorPositive query should return expected results for TrainBenchmark inject-1"
    (doseq [proto [`rsp-proto-1 `rsp-proto-2]]
      (let [inject-1 (tb-loader/load 'inject-1)
            results (tufte/profile {} (tufte/p (keyword (str 'tb-inject-1 '- (utils/short-name proto)))
                                               (into [] (pattern/run (-> proto resolve deref) [:route :follows :sw-p :target :sw :monitored-by :sensor] {:indexer inject-1}))))]
        (is (= 112 (count results)))))))
