(ns ingraph.sre.queries.connectedsegments-test
  (:require [clojure.test :refer :all]
            [sre.plan.pattern :as pattern]
            [sre.plan.constraint :as c]
            [sre.core :refer :all]
            [ingraph.sre.config :refer :all])
  (:import (ingraph.ire Indexer IngraphVertex IngraphEdge)
           (ingraph.sre.config IngraphEnvironment)
           (scala.collection.immutable HashSet HashMap)
           (scala Tuple2)))

(def p (pattern/make-pattern [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6] []
                             [(bind Constant [:-segment-label "Segment"])

                              (bind HasLabels [:segment1 :-segment-label])
                              (bind HasLabels [:segment2 :-segment-label])
                              (bind HasLabels [:segment3 :-segment-label])
                              (bind HasLabels [:segment4 :-segment-label])
                              (bind HasLabels [:segment5 :-segment-label])
                              (bind HasLabels [:segment6 :-segment-label])

                              (bind Constant [:-connects-to-type "connectsTo"])

                              (bind DirectedEdge [:segment1 :-connects-to-1 :segment2])
                              (bind DirectedEdge [:segment2 :-connects-to-2 :segment3])
                              (bind DirectedEdge [:segment3 :-connects-to-3 :segment4])
                              (bind DirectedEdge [:segment4 :-connects-to-4 :segment5])
                              (bind DirectedEdge [:segment5 :-connects-to-5 :segment6])

                              (bind HasType [:-connects-to-1 :-connects-to-type])
                              (bind HasType [:-connects-to-2 :-connects-to-type])
                              (bind HasType [:-connects-to-3 :-connects-to-type])
                              (bind HasType [:-connects-to-4 :-connects-to-type])
                              (bind HasType [:-connects-to-5 :-connects-to-type])

                              (bind DirectedEdge [:segment1 :-monitored-by-1 :sensor])
                              (bind DirectedEdge [:segment2 :-monitored-by-2 :sensor])
                              (bind DirectedEdge [:segment3 :-monitored-by-3 :sensor])
                              (bind DirectedEdge [:segment4 :-monitored-by-4 :sensor])
                              (bind DirectedEdge [:segment5 :-monitored-by-5 :sensor])
                              (bind DirectedEdge [:segment6 :-monitored-by-6 :sensor])

                              (bind Constant [:-monitored-by-type "monitoredBy"])

                              (bind HasType [:-monitored-by-1 :-monitored-by-type])
                              (bind HasType [:-monitored-by-2 :-monitored-by-type])
                              (bind HasType [:-monitored-by-3 :-monitored-by-type])
                              (bind HasType [:-monitored-by-4 :-monitored-by-type])
                              (bind HasType [:-monitored-by-5 :-monitored-by-type])
                              (bind HasType [:-monitored-by-6 :-monitored-by-type])

                              (bind Constant [:-not= not=])

                              (bind GenBinaryAssertion [:-connects-to-1 :-connects-to-2 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-1 :-connects-to-3 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-1 :-connects-to-4 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-1 :-connects-to-5 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-2 :-connects-to-3 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-2 :-connects-to-4 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-2 :-connects-to-5 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-3 :-connects-to-4 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-3 :-connects-to-5 :-not=])
                              (bind GenBinaryAssertion [:-connects-to-4 :-connects-to-5 :-not=])

                              (bind GenBinaryAssertion [:-monitored-by-1 :-monitored-by-2 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-1 :-monitored-by-3 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-1 :-monitored-by-4 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-1 :-monitored-by-5 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-1 :-monitored-by-6 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-2 :-monitored-by-3 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-2 :-monitored-by-4 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-2 :-monitored-by-5 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-2 :-monitored-by-6 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-3 :-monitored-by-4 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-3 :-monitored-by-5 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-3 :-monitored-by-6 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-4 :-monitored-by-5 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-4 :-monitored-by-6 :-not=])
                              (bind GenBinaryAssertion [:-monitored-by-5 :-monitored-by-6 :-not=])]))

(def connected-segments-query (pattern/compile p Ingraph {:k 1}))

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

(deftest test-connected-segments
  (testing "ConnectedSegments query should return 1 row"
    (is (= (count (into [] (pattern/run connected-segments-query
                             [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6]
                             {:indexer indexer})))
           1))))
