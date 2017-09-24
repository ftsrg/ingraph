(ns sre.plan.config-2
  (:refer-clojure :exclude [name])
  (:require [sre.plan.config :refer :all]
            [sre.plan.constraint :refer [defconstraint]]
            [sre.plan.op :refer [defop]]
            [sre.plan.estimation :refer :all]))

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

(defweight GetVertices [op] 5)
(defweight GetEdges [op] 5)
(defweight GetEdgesByType [op] 4)
(defweight ExtendOut [op] 2)
(defweight ExtendIn [op] 2)

(defrecord CostCalculator [c p]
  Cost
  (update-cost [this weight]
    (as-> this this
          (update-in this [:p] #(* % weight))
          (update-in this [:c] #(+ % (:p this)))))
  Comparable
  (compareTo [this other] (compare c (:c other))))

(def cost-calculator (->CostCalculator 0 1))

(def weight-calculator
  (reify Weight
    (get-weight [this op-binding constraint-lookup]
      ((weight Basic) op-binding))))


