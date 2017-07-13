(ns sre.constraint-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [sre.constraint :refer :all]
            ))

(defconstraint Element [element])
(defconstraint Edge [edge] :implies Element [edge])
(defconstraint Vertex [vertex] :implies Element [vertex])
(defconstraint DirectedEdge [source edge target] :implies
               Vertex [source]
               Edge [edge]
               Vertex [target])

(defspec directed-edge-implies-work
         100
         (prop/for-all [x gen/int y gen/int z gen/int ]
                       (= (implies (bind DirectedEdge x y z))
                          (-> #{}
                              (conj (bind Vertex x))
                              (conj (bind Edge y))
                              (conj (bind Vertex z))
                              )
                          )))


