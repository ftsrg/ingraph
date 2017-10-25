(ns ingraph.sre.queries.poslength-test
  (:require [clojure
             [pprint :as pprint]
             [test :refer :all]]
            [ingraph.sre.queries
             [poslength-pattern :as pl]
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


(def pl-proto-1 (compile-pattern 'pl/p-1 5))

(comment
  "eval this to get the tasks -->" (pprint/pprint (utils/get-tasks pl-proto-1)))

(def pl-proto-2 (compile-pattern 'pl/p-2 5))

(comment
  "eval this to get the tasks -->" (pprint/pprint (utils/get-tasks pl-proto-2)))

(deftest test-poslength-smoke
  (testing "PosLength query should return 1 row"
    (for [proto ['pl-proto-1 'pl-proto-2]]
      (tufte/profile {}
                     (let [smoke (doto (Indexer.)
                                   (.addVertex (IngraphVertex. 0
                                                               (-> (HashSet.) (.$plus "Segment"))
                                                               (-> (HashMap.) (.$plus (Tuple2. "length" -1)))))
                                   (.addVertex (IngraphVertex. 1
                                                               (-> (HashSet.) (.$plus "Segment"))
                                                               (-> (HashMap.) (.$plus (Tuple2. "length" 9))))))]
                       (is (= (count (tufte/p (keyword (str 'smoke '- proto))
                                              (into [] (pattern/run (-> proto resolve deref) [:segment :length] {:indexer smoke}))))
                              1)))))))

(def tb-inject-1 (tb-loader/load 'inject-1))

(deftest test-poslength-tb-inject-1
  (testing "PosLength query should return expected results for TrainBenchmark inject-1"
    (tufte/profile
     {}
     (for [proto ['pl-proto-1 'pl-proto-2]]
       (let [inject-1 (tb-loader/load 'inject-1)
             results (tufte/p (keyword (str 'rb-inject-1 '- proto))
                              (into [] (pattern/run (-> proto resolve deref) [:segment :length] {:indexer inject-1})))]
         (is (= (count results) 12)))))))
