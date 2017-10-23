(ns ingraph.sre.queries.poslength-test
  (:require [clojure
             [pprint :as pprint]
             [test :refer :all]]
            [ingraph.sre.ingraph-proto-1 :refer :all]
            [ingraph.sre.queries
             [utils :as utils]]
            [sre.core :refer :all]
            [sre.plan.pattern :as pattern])
  (:import [ingraph.ire Indexer IngraphVertex]
           [scala.collection.immutable HashMap HashSet]
           scala.Tuple2))

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

(comment (pprint/pprint (utils/get-tasks poslength-query)))

(def indexer (doto (Indexer.)
               (.addVertex (IngraphVertex. 0
                                           (-> (HashSet.) (.$plus "Segment"))
                                           (-> (HashMap.) (.$nplus (Tuple2. "length" -1)))))
               (.addVertex (IngraphVertex. 1
                                           (-> (HashSet.) (.$plus "Segment"))
                                           (-> (HashMap.) (.$plus (Tuple2. "length" 9)))))))

(deftest test-poslength
  (testing "PosLength query should return 1 row"
    (is (= (count (into [] (pattern/run poslength-query [:segment :length] {:indexer indexer}))) 1))))
