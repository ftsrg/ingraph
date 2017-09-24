(ns sre.plan.constraint-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [sre.plan.constraint :refer :all]))

(defconstraint Element [element])
(defconstraint Edge [edge] < Element [edge])
(defconstraint Vertex [vertex] < Element [vertex])
(defconstraint DirectedEdge [source edge target]
               < Vertex [source]
                 Edge [edge]
                 Vertex [target])

(defspec directed-edge-implies-work
         100
         (prop/for-all [x gen/int y gen/int z gen/int ]
                       (= (implies* (bind DirectedEdge x y z))
                          (conj #{}
                                (bind DirectedEdge x y z)
                                (bind Vertex x)
                                (bind Edge y)
                                (bind Vertex z)
                                (bind Element x)
                                (bind Element y)
                                (bind Element z)))))


