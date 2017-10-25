(ns ingraph.sre.queries.poslength-pattern
  (:require [ingraph.sre
             [ingraph-proto-1 :as proto-1]
             [ingraph-proto-2 :as proto-2]]
            [sre.core :refer :all]
            [sre.plan.pattern :as pattern]))

(def p-1 {:config proto-1/Ingraph
          :pattern (pattern/make-pattern [:segment :length] []
                                         [(bind proto-1/Vertex [:segment])
                                          (bind proto-1/HasLabels [:segment :-segment-label])
                                          (bind proto-1/Constant [:-segment-label "Segment"])
                                          (bind proto-1/Property [:segment :-length-key :length])
                                          (bind proto-1/Constant [:-length-key "length"])
                                          (bind proto-1/GenBinaryAssertion [:length :-0 :-<=])
                                          (bind proto-1/Constant [:-0 0])
                                          (bind proto-1/Constant [:-<= <=])])})
(def p-2 {:config proto-2/Ingraph
          :pattern (pattern/make-pattern [:segment :length] []
                                         [(bind proto-2/Vertex [:segment])
                                          (bind proto-2/HasLabels [:segment :-segment-label])
                                          (bind proto-2/Constant [:-segment-label "Segment"])
                                          (bind proto-2/Property [:segment :-length-key :length])
                                          (bind proto-2/Constant [:-length-key "length"])
                                          (bind proto-2/GenBinaryAssertion [:length :-0 :-<=])
                                          (bind proto-2/Constant [:-0 0])
                                          (bind proto-2/Constant [:-<= <=])])})
