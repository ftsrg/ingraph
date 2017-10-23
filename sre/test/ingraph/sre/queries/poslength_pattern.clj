(ns ingraph.sre.queries.poslength-pattern
  (:require [ingraph.sre.ingraph-proto-1 :refer :all]
            [sre.core :refer :all]
            [sre.plan.pattern :as pattern]))

(def p (pattern/make-pattern [:segment :length] []
                             [(bind Vertex [:segment])
                              (bind HasLabels [:segment :-segment-label])
                              (bind Constant [:-segment-label "Segment"])
                              (bind Property [:segment :-length-key :length])
                              (bind Constant [:-length-key "length"])
                              (bind GenBinaryAssertion [:length :-0 :-<=])
                              (bind Constant [:-0 0])
                              (bind Constant [:-<= <=])]))
