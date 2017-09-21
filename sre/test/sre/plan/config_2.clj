(ns sre.plan.config-2
  (:require [sre.plan.dsl.config :refer [defconfig]]
            [sre.plan.dsl.constraint :refer [defconstraint]]
            [sre.plan.dsl.op :refer [defop]]
            [sre.plan.dsl.estimation :refer :all]))

(defconfig Basic)

(defconstraint Known [known])
(defconstraint Element [element] < Known [element])
(defconstraint Edge [edge] < Element [edge])
(defconstraint Vertex [vertex] < Element [vertex])
(defconstraint DirectedEdge [source edge target]
               < Vertex [source]
                 Edge [edge]
                 Vertex [target])
(defconstraint HasType [edge type] < Edge [edge] Known [type])

(defop GetVertices [vertex] -> Vertex [vertex])
(defop GetEdges [source edge target] -> DirectedEdge [source edge target])
(defop GetEdgesByType [source edge target type]
       Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defop ExtendOut [source edge target]
       Vertex [source] -> DirectedEdge [source edge target])
(defop ExtendIn [target edge source]
       Vertex [target] -> DirectedEdge [source edge target])

(defmulti weight (fn [op] (:type op)))

(defmethod weight GetVertices [op] 5)
(defmethod weight GetEdges [op] 5)
(defmethod weight GetEdgesByType [op] 4)
(defmethod weight ExtendOut [op] 2)
(defmethod weight ExtendIn [op] 2)

(defrecord CostCalculator [c p]
  Cost
  (update-cost [this weight]
    (as-> this this
      (update-in this [:p] #(* % weight))
      (update-in this [:c] #(+ % (:p this)))))
  Comparable
  (compareTo [this other] (compare c (:c other))))

(defrecord WeightCalculator [w]
  Weight
  (update-weight [this bound-op constraint-lookup]
    (assoc-in this [:w] (weight bound-op)))
  (get-weight [this] w))


