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
                                              (bind HasLabels [:segment :_segment])
                                              (bind Constant [:_segment "Segment"])
                                              (bind Property [:segment :_length_key :length])
                                              (bind Constant [:_length_key "length"])
                                              (bind GenBinaryAssertion [:length :_0 :_<=])
                                              (bind Constant [:_0 0])
                                              (bind Constant [:_<= <=])]))


(def poslength-query (pattern/compile poslength-pattern Ingraph {:k 5}))

(((nth poslength-query 3) (nth poslength-query 1)))

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
