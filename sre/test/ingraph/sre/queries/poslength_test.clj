(ns ingraph.sre.queries.poslength-test
  (:require [clojure.test :refer :all]
            [sre.plan.pattern :as pattern]
            [sre.plan.constraint :as c]
            [sre.core :refer :all]
            [ingraph.sre.config :refer :all])
  (:import (ingraph.ire Indexer IngraphVertex IngraphEdge)
           (ingraph.sre.config IngraphEnvironment)
           (scala.collection.immutable HashSet HashMap)
           (scala Tuple2)))
(def poslength-pattern (pattern/make-pattern [:segment :length] []
                                             [(bind Vertex [:segment])
                                              (bind HasLabels [:segment :-segment-label])
                                              (bind Constant [:-segment-label "Segment"])
                                              (bind Property [:segment :-length-key :length])
                                              (bind Constant [:-length-key "length"])
                                              (bind GenBinaryAssertion [:length :-0 :-<=])
                                              (bind Constant [:-0 0])
                                              (bind Constant [:-<= <=])]))


(def poslength-query (pattern/compile poslength-pattern Ingraph {:k 5}))

(def indexer (doto (Indexer.)
               (.addVertex (IngraphVertex. 0
                                           (-> (HashSet.) (.$plus "Segment"))
                                           (-> (HashMap.) (.$plus (Tuple2. "length" -1)))))
               (.addVertex (IngraphVertex. 1
                                           (-> (HashSet.) (.$plus "Segment"))
                                           (-> (HashMap.) (.$plus (Tuple2. "length" 9)))))))

(deftest test-poslength
  (testing "PosLength query should return 1 row"
    (is (= (count (into [] (pattern/run poslength-query [:segment :length] {:indexer indexer}))) 1))))
