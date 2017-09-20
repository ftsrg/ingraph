(ns sre.plan.dsl.constraint-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [sre.plan.dsl.constraint :refer :all]))

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
                       (= (implies* (->ConstraintBinding #'DirectedEdge [x y z]))
                          (-> #{}
                              (conj (->ConstraintBinding #'DirectedEdge [x y z]))
                              (conj (->ConstraintBinding #'Vertex [x]))
                              (conj (->ConstraintBinding #'Edge [y]))
                              (conj (->ConstraintBinding #'Vertex [z]))
                              (conj (->ConstraintBinding #'Element [x]))
                              (conj (->ConstraintBinding #'Element [y]))
                              (conj (->ConstraintBinding #'Element [z]))))))


