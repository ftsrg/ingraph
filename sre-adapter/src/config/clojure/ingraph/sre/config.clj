(ns ingraph.sre.config
  "Contains the main configuration of constraints, operations and others
  available in ingraph"
  (:refer-clojure :exclude [name])
  (:require
    [sre.plan.constraint :refer [defconstraint]]
    [sre.plan.op :refer [defop]]
    [sre.plan.config :refer :all]
    [sre.plan.estimation :refer :all]
    [sre.plan.task :refer :all])
  (:import (ingraph.ire Indexer IngraphVertex IngraphEdge)
           (java.util Iterator)
           (clojure.lang PersistentHashMap)))

;; Performance-critical code. General good advice: look out for reflection warnings
;; in the tests, add type hints when they occur.
(set! *warn-on-reflection* true)

(defn ??? [] (throw (Exception. "Not implemented yet")))

(defrecord IngraphEnvironment [^Indexer indexer])

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
(defweight GetVertices [op] 5)
(deftask GetVertices
         (let [[v] (:bindings op)
               ^IngraphEnvironment env (:env state)
               ^PersistentHashMap var-lkp (:var-lkp state)
               ^Indexer indexer (:indexer env)
               iterator (.verticesJava indexer)]
           (->> (iterator-seq iterator)
                (map #(assoc var-lkp v %)))))

; TODO should work with multiple labels, but no indexer support yet [#184]
(defop
  GetVerticesByLabels [vertex labels]
  Known [labels] -> Vertex [vertex] HasLabels [vertex labels])
(defweight
  GetVerticesByLabels [op] 3)
(deftask
  GetVerticesByLabels
  (let [[v l] (:bindings op)
        ^IngraphEnvironment env (:env state)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^Indexer indexer (:indexer env)
        ^String label (var-lkp l)
        ^Iterator iterator (.verticesByLabelJava indexer label)]
    (->> (iterator-seq iterator)
         (map #(assoc var-lkp v %)))))

(defop
  CheckLabels [vertex labels]
  Vertex [vertex] Known [labels] -> HasLabels [vertex labels])
(defweight
  CheckLabels [op] 0.5)
(deftask
  CheckLabels
  (let [[v l] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex vertex (var-lkp v)
        ^String label (var-lkp l)]
    (if (.contains (.labels vertex) label) (list var-lkp) ())))

(defop
  GetEdges [source edge target] -> DirectedEdge [source edge target])
(defweight
  GetEdges [op] 5)
(deftask
  GetEdges
  (let [[v e w] (:bindings op)
        ^IngraphEnvironment env (:env state)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^Indexer indexer (:indexer env)
        ^Iterator iterator (.edgesJava indexer)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     src (.sourceVertex edge)
                     tgt (.targetVertex edge)]
                 (assoc var-lkp v src e edge w tgt))))))

(defop
  GetEdgesByType [source edge target type]
  Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  GetEdgesByType [op] 3)
(deftask
  GetEdgesByType
  (let [[v e w t] (:bindings op)
        ^IngraphEnvironment env (:env state)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^Indexer indexer (:indexer env)
        ^String type (var-lkp t)
        ^Iterator iterator (.edgesByTypeJava indexer type)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     src (.sourceVertex edge)
                     tgt (.targetVertex edge)]
                 (assoc var-lkp v src e edge w tgt))))))

(defop
  CheckType [edge type]
  Edge [edge] Known [type] -> HasType [edge type])
(defweight
  CheckType [op] 0.5)
(deftask
  CheckType
  (let [[e t] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphEdge edge (var-lkp e)
        ^String type (var-lkp t)]
    (if (= (.type edge) type) (list var-lkp) ())))

(defop
  AccessPropertyByKey [element key val]
  Element [element] Known [key] -> Property [element key val])
(defweight
  AccessPropertyByKey [op] 1)
(deftask
  AccessPropertyByKey (???))

(defop
  ExtendOut [source edge target]
  Vertex [source] -> DirectedEdge [source edge target])
(defweight
  ExtendOut [op] 3)
(deftask
  ExtendOut
  (let [[v e w] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex vertex (var-lkp v)
        ^Iterator iterator (.edgesOutJavaIterator vertex)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex tgt (.targetVertex edge)]
                 (assoc var-lkp e edge w tgt))))))

(defop
  ExtendIn [target edge source]
  Vertex [target] -> DirectedEdge [source edge target])
(defweight
  ExtendIn [op] 3)
(deftask
  ExtendIn
  (let [[v e w] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex vertex (var-lkp v)
        ^Iterator iterator (.edgesInJavaIterator vertex)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex src (.sourceVertex edge)]
                 (assoc var-lkp e edge w src))))))

(defop
  ExtendOutByType [source edge target type]
  Vertex [source] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  ExtendOutByType [op] 2)
(deftask
  ExtendOutByType
  (let [[v e w t] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex vertex (var-lkp v)
        ^String type (var-lkp t)
        ^Iterator iterator (.edgesOutByTypeJavaIterator vertex type)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex tgt (.targetVertex edge)]
                 (assoc var-lkp e edge w tgt))))))

(defop
  ExtendInByType [target edge source type]
  Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  ExtendInByType [op] 3)
(deftask
  ExtendInByType
  (let [[v e w t] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex vertex (var-lkp v)
        ^String type (var-lkp t)
        ^Iterator iterator (.edgesInByTypeJavaIterator vertex type)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex src (.sourceVertex edge)]
                 (assoc var-lkp e edge w src))))))

(defop
  Join [source edge target]
  Vertex [source] Vertex [target] -> DirectedEdge [source edge target])
(defweight
  Join [op] 10)
(deftask
  Join
  (let [[v e w] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex source (var-lkp v)
        ^IngraphVertex target (var-lkp w)
        ;TODO No indexer support so we'll fall back to filtering :(
        ^Iterator iterator (.edgesInJavaIterator target)]
    (->> (iterator-seq iterator)
         ; filter for matches and map to lookup in one function
         (mapcat #(let [^IngraphEdge edge %
                        ^IngraphVertex src (.sourceVertex edge)]
                    (if (= src source)
                      (list (assoc var-lkp e edge v src))
                      ()))))))

(defop
  JoinByType [source edge target type]
  Vertex [source] Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  JoinByType [op] 5)
(deftask
  JoinByType
  (let [[v e w t] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex source (var-lkp v)
        ^IngraphVertex target (var-lkp w)
        ^String type (var-lkp t)
        ; No indexer support so will fall back to filtering :(
        ^Iterator iterator (.edgesInByTypeJavaIterator target type)]
    (->> (iterator-seq iterator)
         ; filter for matches and map to lookup in one function
         (mapcat #(let [^IngraphEdge edge %
                        ^IngraphVertex src (.sourceVertex edge)]
                    (if (= src source)
                      (list (assoc var-lkp e edge v src))
                      ()))))))

; Does this make any sense at all?
(defop
  CheckDirectedEdge [source edge target]
  Vertex [source] Edge [edge] Vertex [target] -> DirectedEdge [source edge target])
(defweight
  CheckDirectedEdge [op] 0.5)
(deftask CheckDirectedEdge
         (let [[v e w] (:bindings op)
               ^PersistentHashMap var-lkp (:var-lkp state)
               ^IngraphVertex source (var-lkp v)
               ^IngraphVertex target (var-lkp w)
               ^IngraphEdge edge (var-lkp e)]
           (if (and (= (.sourceVertex edge) source)
                    (= (.targetVertex edge) target))
             (list var-lkp)
             ())))

(defop
  CheckDirectedEdgeByType [source edge target type]
  Vertex [source] Edge [edge] Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  CheckDirectedEdgeByType [op] 0.3)
(deftask
  CheckDirectedEdgeByType
  (let [[v e w t] (:bindings op)
        ^PersistentHashMap var-lkp (:var-lkp state)
        ^IngraphVertex source (var-lkp v)
        ^IngraphVertex target (var-lkp w)
        ^IngraphEdge edge (var-lkp e)
        ^String type (var-lkp t)]
    (if (and (= (.sourceVertex edge) source)
             (= (.targetVertex edge) target)
             (= (.type edge) t))
      (list var-lkp)
      ())))


(defop
  EvalGenUnaryAssertion [x cond]
  Known [x] Known [cond] -> GenUnaryAssertion [x cond])
(defweight
  EvalGenUnaryAssertion [op] 0.1)
(deftask
  EvalGenUnaryAssertion
  (let [^PersistentHashMap var-lkp (:var-lkp state)
        [x ucond] (map var-lkp (:bindings op))]
    (if (ucond x) (list var-lkp) ())))


(defop
  EvalGenBinaryAssertion [x y cond]
  Known [x] Known [y] Known [cond] -> GenBinaryAssertion [x y cond])
(defweight
  EvalGenBinaryAssertion [op] 0.1)
(deftask
  EvalGenBinaryAssertion
  (let [^PersistentHashMap var-lkp (:var-lkp state)
        [x y bicond] (map var-lkp (:bindings op))]
    (if (bicond x y) (list var-lkp) ())))

