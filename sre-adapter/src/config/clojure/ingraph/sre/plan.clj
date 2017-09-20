(ns ingraph.sre.plan
    "Contains the main configuration of constraints, operations and others
    available in ingraph"
    (:require [sre.plan.dsl.constraint :refer [defconstraint]]
      [sre.plan.dsl.op :refer [defop]]
      [sre.plan.dsl.config :refer :all]))

(defconfig Ingraph)

(defconstraint Known [known])
(defconstraint Element [element] < Known [element])
(defconstraint Edge [edge] < Element [edge])
(defconstraint Vertex [vertex] < Element [vertex])
(defconstraint HasLabels [vertex labels] < Vertex [vertex] Known [labels])
(defconstraint HasType [edge type] < Edge [edge] Known [type])
(defconstraint Property [element key value]
               < Element [element] Known [key] Known [value])
(defconstraint DirectedEdge [source edge target] <
               Vertex [source]
               Edge [edge]
               Vertex [target])
(defconstraint GenUnaryAssertion [x cond] < Known [x] Known [cond])
(defconstraint GenBinaryAssertion [x y cond] < Known [x] Known [y] Known [cond])

(defop GetVertices [vertex] -> Vertex [vertex])
(defop GetVerticesByLabels [vertex labels]
       Known [labels] -> Vertex [vertex] HasLabels [vertex labels])
(defop CheckLabels [vertex labels]
       Vertex [vertex] Known [labels] -> HasLabels [vertex labels])
(defop GetEdges [source edge target] -> DirectedEdge [source edge target])
(defop GetEdgesByType [source edge target type]
       Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defop CheckType [edge type]
       Edge [edge] Known [type] -> HasType [edge type])
(defop AccessPropertyByKey [element key val]
       Element [element] Known [key] -> Property [element key val])
(defop ExtendOut [source edge target]
       Vertex [source] -> DirectedEdge [source edge target])
(defop ExtendIn [target edge source]
       Vertex [target] -> DirectedEdge [source edge target])
(defop ExtendOutByType [source edge target type]
       Vertex [source] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defop ExtendInByType [target edge source type]
       Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defop Join [source edge target]
       Vertex [source] Vertex [target] -> DirectedEdge [source edge target])
(defop JoinByType [source edge target type]
       Vertex [source] Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defop CheckDirectedEdge [source edge target]
       Vertex [source] Edge [edge] Vertex [target] -> DirectedEdge [source edge target])
(defop CheckDirectedEdgeByType [source edge target type]
       Vertex [source] Edge [edge] Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defop EvalGenUnaryAssertion [x cond]
       Known [x] Known [cond] -> GenUnaryAssertion [x cond])
(defop EvalGenBinaryAssertion [x y cond]
       Known [x] Known [y] Known [cond] -> GenBinaryAssertion [x y cond])

