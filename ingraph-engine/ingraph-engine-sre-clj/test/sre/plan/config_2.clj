(ns sre.plan.config-2
    (:require [sre.plan.dsl.config :refer [defconfig]]
              [sre.plan.dsl.constraint :refer [defconstraint]]
              [sre.plan.dsl.op :refer [defop defweight]]))

(defconfig Basic)

(defconstraint Element [element])
(defconstraint Edge [edge] :implies Element [edge])
(defconstraint Vertex [vertex] :implies Element [vertex])
(defconstraint DirectedEdge [source edge target] :implies
               Vertex [source]
               Edge [edge]
               Vertex [target])

(defop GetVertices [vertex]
       :satisfies Vertex [vertex])
(defop GetEdges [source edge target]
       :satisfies DirectedEdge [source edge target])
(defop ExtendOut [source edge target]
       :requires Vertex [source]
       :satisfies DirectedEdge [source edge target])
(defop ExtendIn [target edge source]
       :requires Vertex [target]
       :satisfies DirectedEdge [source edge target])

(defweight GetVertices [op _] 5)
(defweight GetEdges [op _] 5)
(defweight ExtendOut [op _] 2)
(defweight ExtendIn [op _] 2)
