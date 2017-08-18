(ns ingraph.sre.execution-test
  (:require [clojure.test :refer :all]
            [sre.execution.dsl :refer :all]
            [ingraph.sre.execution :refer :all]
            [clojure.pprint :refer :all])
  (:import [ingraph.ire Indexer IngraphVertex]
           [ingraph.sre.execution IngraphEnvironment]
           [scala.collection.immutable HashSet]))

(set! *warn-on-reflection* true)

(def ^IngraphVertex person0 (IngraphVertex. 0 (.$plus (HashSet.) "person") nil))
(def ^IngraphVertex person1 (IngraphVertex. 1 (.$plus (HashSet.) "person") nil))

(deftest test-get-vertices
  ; Indexer is side-effectful so recreate it in every test
  ; to avoid headaches.
  (testing "GetVertices"
    (testing "should return nothing"
     (let [indexer (Indexer.)
           env (IngraphEnvironment. indexer)
           task (->GetVertices [7])]
       (is (empty? (call task env {})))))

    (testing "should return vertices"
      (let [^Indexer indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->GetVertices [7])]
        (.addVertex indexer person0)
        (.addVertex indexer person1)
        (is (= #{{7 person0} {7 person1}}
               (into #{} (call task env {}))))))))
