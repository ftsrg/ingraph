(ns ingraph.sre.queries.connectedsegments-test
  (:require [clojure
             [pprint :as pprint]
             [test :refer :all]]
            [ingraph.sre.ingraph-proto-1 :refer :all]
            [ingraph.sre.queries
             [connectedsegments-pattern :as cs]
             [utils :as utils]]
            [ingraph.sre.queries.utils.tb-loader :as tb-loader]
            [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte])
  (:import [ingraph.ire Indexer IngraphEdge IngraphVertex]
           [scala.collection.immutable HashMap HashSet]))

(def connected-segments-query (pattern/compile cs/p Ingraph {:k 5}))

(pprint/pprint (:subtasks (utils/get-tasks connected-segments-query)))
(def segment-1 (IngraphVertex. 1 (-> (HashSet.) (.$plus "Segment")) (HashMap.)))
(def segment-2 (IngraphVertex. 2 (-> (HashSet.) (.$plus "Segment")) (HashMap.)))
(def segment-3 (IngraphVertex. 3 (-> (HashSet.) (.$plus "Segment")) (HashMap.)))
(def segment-4 (IngraphVertex. 4 (-> (HashSet.) (.$plus "Segment")) (HashMap.)))
(def segment-5 (IngraphVertex. 5 (-> (HashSet.) (.$plus "Segment")) (HashMap.)))
(def segment-6 (IngraphVertex. 6 (-> (HashSet.) (.$plus "Segment")) (HashMap.)))

(def connects-to-12 (IngraphEdge. 7 segment-1 segment-2 "connectsTo" (HashMap.)))
(def connects-to-23 (IngraphEdge. 8 segment-2 segment-3 "connectsTo" (HashMap.)))
(def connects-to-34 (IngraphEdge. 9 segment-3 segment-4 "connectsTo" (HashMap.)))
(def connects-to-45 (IngraphEdge. 10 segment-4 segment-5 "connectsTo" (HashMap.)))
(def connects-to-56 (IngraphEdge. 11 segment-5 segment-6 "connectsTo" (HashMap.)))

(def sensor (IngraphVertex. 12 (-> (HashSet.) (.$plus "Sensor")) (HashMap.)))

(def monitored-by-1 (IngraphEdge. 13 segment-1 sensor "monitoredBy" (HashMap.)))
(def monitored-by-2 (IngraphEdge. 13 segment-2 sensor "monitoredBy" (HashMap.)))
(def monitored-by-3 (IngraphEdge. 13 segment-3 sensor "monitoredBy" (HashMap.)))
(def monitored-by-4 (IngraphEdge. 13 segment-4 sensor "monitoredBy" (HashMap.)))
(def monitored-by-5 (IngraphEdge. 13 segment-5 sensor "monitoredBy" (HashMap.)))
(def monitored-by-6 (IngraphEdge. 13 segment-6 sensor "monitoredBy" (HashMap.)))

(def indexer (doto (Indexer.)
               (.addVertex segment-1)
               (.addVertex segment-2)
               (.addVertex segment-3)
               (.addVertex segment-4)
               (.addVertex segment-5)
               (.addVertex segment-6)
               (.addEdge connects-to-12)
               (.addEdge connects-to-23)
               (.addEdge connects-to-34)
               (.addEdge connects-to-45)
               (.addEdge connects-to-56)
               (.addEdge monitored-by-1)
               (.addEdge monitored-by-2)
               (.addEdge monitored-by-3)
               (.addEdge monitored-by-4)
               (.addEdge monitored-by-5)
               (.addEdge monitored-by-6)))

(tufte/add-basic-println-handler! {})
(tufte/profile {}
               (dotimes [_ 1]
                 (tufte/p :smoke-count (count (into [] (pattern/run connected-segments-query
                                                         [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6]
                                                         {:indexer indexer}))))))

(def trainbenchmark (tb-loader/load))
(tufte/profile {}
               (dotimes [_ 1]
                 (tufte/p :tb-inject-1-count (count (into [] (pattern/run connected-segments-query
                                                               [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6]
                                                               {:indexer trainbenchmark}))))))

(deftest test-connected-segments
  (testing "ConnectedSegments query should return 1 row"
    (is (= (count (into [] (pattern/run connected-segments-query
                             [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6]
                             {:indexer indexer})))
           1))))
