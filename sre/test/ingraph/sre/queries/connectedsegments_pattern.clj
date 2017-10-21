(ns ingraph.sre.queries.connectedsegments-pattern
  (:require [ingraph.sre.ingraph-proto-1 :refer :all]
            [sre.core :refer :all]
            [sre.plan.pattern :as pattern]))

(def p-1
  "This was my first shot."
  (pattern/make-pattern [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6] []
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
