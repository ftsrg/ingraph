(ns sre.plan.config-2
  (:refer-clojure :exclude [name])
  (:require [sre.plan.config :refer :all]
            [sre.plan.constraint :refer [defconstraint]]
            [sre.plan.op :refer [defop]]
            [sre.plan.task :refer [deftask]]
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

(defweight GetVertices [op & rest] 5)
(defweight GetEdges [op & rest] 5)
(defweight GetEdgesByType [op & rest] 4)
(defweight ExtendOut [op & rest] 2)
(defweight ExtendIn [op & rest] 2)

(deftask GetVertices (let [[v] bindings]
                       [(variables v 1)
                        (variables v 2)
                        (variables v 3)]))

(deftask GetEdges (let [[v e w t] bindings]
                    [(variables v 1 e "1->2" w 2)]))

(deftask ExtendOut
         (let [[v e w] bindings]
           (if (= (variables v) 1)
             [(variables e "1->2" w 2)])))
