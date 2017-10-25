(ns ingraph.sre.queries.connectedsegments-pattern
  (:require [ingraph.sre
             [ingraph-proto-1 :as proto-1]
             [ingraph-proto-2 :as proto-2]]
            [sre.core :refer :all]
            [sre.plan.pattern :as pattern]))

(def p-1 {:config  proto-1/Ingraph
          :pattern (pattern/make-pattern [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6] []
                                         [(bind proto-1/Constant [:-segment-label "Segment"])

                                          (bind proto-1/HasLabels [:segment1 :-segment-label])
                                          (bind proto-1/HasLabels [:segment2 :-segment-label])
                                          (bind proto-1/HasLabels [:segment3 :-segment-label])
                                          (bind proto-1/HasLabels [:segment4 :-segment-label])
                                          (bind proto-1/HasLabels [:segment5 :-segment-label])
                                          (bind proto-1/HasLabels [:segment6 :-segment-label])

                                          (bind proto-1/Constant [:-connects-to-type "connectsTo"])

                                          (bind proto-1/DirectedEdge [:segment1 :-connects-to-1 :segment2])
                                          (bind proto-1/DirectedEdge [:segment2 :-connects-to-2 :segment3])
                                          (bind proto-1/DirectedEdge [:segment3 :-connects-to-3 :segment4])
                                          (bind proto-1/DirectedEdge [:segment4 :-connects-to-4 :segment5])
                                          (bind proto-1/DirectedEdge [:segment5 :-connects-to-5 :segment6])

                                          (bind proto-1/HasType [:-connects-to-1 :-connects-to-type])
                                          (bind proto-1/HasType [:-connects-to-2 :-connects-to-type])
                                          (bind proto-1/HasType [:-connects-to-3 :-connects-to-type])
                                          (bind proto-1/HasType [:-connects-to-4 :-connects-to-type])
                                          (bind proto-1/HasType [:-connects-to-5 :-connects-to-type])

                                          (bind proto-1/DirectedEdge [:segment1 :-monitored-by-1 :sensor])
                                          (bind proto-1/DirectedEdge [:segment2 :-monitored-by-2 :sensor])
                                          (bind proto-1/DirectedEdge [:segment3 :-monitored-by-3 :sensor])
                                          (bind proto-1/DirectedEdge [:segment4 :-monitored-by-4 :sensor])
                                          (bind proto-1/DirectedEdge [:segment5 :-monitored-by-5 :sensor])
                                          (bind proto-1/DirectedEdge [:segment6 :-monitored-by-6 :sensor])

                                          (bind proto-1/Constant [:-monitored-by-type "monitoredBy"])

                                          (bind proto-1/HasType [:-monitored-by-1 :-monitored-by-type])
                                          (bind proto-1/HasType [:-monitored-by-2 :-monitored-by-type])
                                          (bind proto-1/HasType [:-monitored-by-3 :-monitored-by-type])
                                          (bind proto-1/HasType [:-monitored-by-4 :-monitored-by-type])
                                          (bind proto-1/HasType [:-monitored-by-5 :-monitored-by-type])
                                          (bind proto-1/HasType [:-monitored-by-6 :-monitored-by-type])

                                          (bind proto-1/Constant [:-not= not=])

                                          (bind proto-1/GenBinaryAssertion [:-connects-to-1 :-connects-to-2 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-1 :-connects-to-3 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-1 :-connects-to-4 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-1 :-connects-to-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-2 :-connects-to-3 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-2 :-connects-to-4 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-2 :-connects-to-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-3 :-connects-to-4 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-3 :-connects-to-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-connects-to-4 :-connects-to-5 :-not=])

                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-2 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-3 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-4 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-6 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-3 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-4 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-6 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-3 :-monitored-by-4 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-3 :-monitored-by-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-3 :-monitored-by-6 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-4 :-monitored-by-5 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-4 :-monitored-by-6 :-not=])
                                          (bind proto-1/GenBinaryAssertion [:-monitored-by-5 :-monitored-by-6 :-not=])])})

(def p-2 {:config  proto-2/Ingraph
          :pattern (pattern/make-pattern [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6] []
                                         [(bind proto-2/Constant [:-segment-label "Segment"])

                                          (bind proto-2/HasLabels [:segment1 :-segment-label])
                                          (bind proto-2/HasLabels [:segment2 :-segment-label])
                                          (bind proto-2/HasLabels [:segment3 :-segment-label])
                                          (bind proto-2/HasLabels [:segment4 :-segment-label])
                                          (bind proto-2/HasLabels [:segment5 :-segment-label])
                                          (bind proto-2/HasLabels [:segment6 :-segment-label])

                                          (bind proto-2/Constant [:-connects-to-type "connectsTo"])

                                          (bind proto-2/DirectedEdge [:segment1 :-connects-to-1 :segment2])
                                          (bind proto-2/DirectedEdge [:segment2 :-connects-to-2 :segment3])
                                          (bind proto-2/DirectedEdge [:segment3 :-connects-to-3 :segment4])
                                          (bind proto-2/DirectedEdge [:segment4 :-connects-to-4 :segment5])
                                          (bind proto-2/DirectedEdge [:segment5 :-connects-to-5 :segment6])

                                          (bind proto-2/HasType [:-connects-to-1 :-connects-to-type])
                                          (bind proto-2/HasType [:-connects-to-2 :-connects-to-type])
                                          (bind proto-2/HasType [:-connects-to-3 :-connects-to-type])
                                          (bind proto-2/HasType [:-connects-to-4 :-connects-to-type])
                                          (bind proto-2/HasType [:-connects-to-5 :-connects-to-type])

                                          (bind proto-2/DirectedEdge [:segment1 :-monitored-by-1 :sensor])
                                          (bind proto-2/DirectedEdge [:segment2 :-monitored-by-2 :sensor])
                                          (bind proto-2/DirectedEdge [:segment3 :-monitored-by-3 :sensor])
                                          (bind proto-2/DirectedEdge [:segment4 :-monitored-by-4 :sensor])
                                          (bind proto-2/DirectedEdge [:segment5 :-monitored-by-5 :sensor])
                                          (bind proto-2/DirectedEdge [:segment6 :-monitored-by-6 :sensor])

                                          (bind proto-2/Constant [:-monitored-by-type "monitoredBy"])

                                          (bind proto-2/HasType [:-monitored-by-1 :-monitored-by-type])
                                          (bind proto-2/HasType [:-monitored-by-2 :-monitored-by-type])
                                          (bind proto-2/HasType [:-monitored-by-3 :-monitored-by-type])
                                          (bind proto-2/HasType [:-monitored-by-4 :-monitored-by-type])
                                          (bind proto-2/HasType [:-monitored-by-5 :-monitored-by-type])
                                          (bind proto-2/HasType [:-monitored-by-6 :-monitored-by-type])

                                          (bind proto-2/Constant [:-not= not=])

                                          (bind proto-2/GenBinaryAssertion [:-connects-to-1 :-connects-to-2 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-1 :-connects-to-3 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-1 :-connects-to-4 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-1 :-connects-to-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-2 :-connects-to-3 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-2 :-connects-to-4 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-2 :-connects-to-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-3 :-connects-to-4 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-3 :-connects-to-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-connects-to-4 :-connects-to-5 :-not=])

                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-2 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-3 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-4 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-1 :-monitored-by-6 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-3 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-4 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-2 :-monitored-by-6 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-3 :-monitored-by-4 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-3 :-monitored-by-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-3 :-monitored-by-6 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-4 :-monitored-by-5 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-4 :-monitored-by-6 :-not=])
                                          (bind proto-2/GenBinaryAssertion [:-monitored-by-5 :-monitored-by-6 :-not=])])})
