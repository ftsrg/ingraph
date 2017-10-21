(ns ingraph.sre.config
  "Contains the main configuration of constraints, operations and others
  available in ingraph"
  (:refer-clojure :exclude [name])
  (:require
   [sre.plan.constraint :refer [defconstraint]]
   [sre.plan.op :refer [defop]]
   [sre.plan.config :refer :all]
   [sre.plan.estimation :refer :all]
   [sre.plan.task :refer [deftask]])
  (:import (scala.runtime AbstractFunction0)
           (ingraph.ire Indexer IngraphVertex IngraphEdge IngraphElement)
           (java.util Iterator)
           (clojure.lang IPersistentMap)))

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
(defconstraint Constant [x value] < Known [x])

(defop GetVertices [vertex] -> Vertex [vertex])
(defweight GetVertices [op & rest] 1000)
(deftask GetVertices
  (let [[v] bindings
        ^Indexer indexer (:indexer ^IPersistentMap ctx)
        iterator (.verticesJava indexer)]
    (->> (iterator-seq iterator)
         (map #(assoc variables v %)))))

;; TODO should work with multiple labels, but no indexer support yet [#184]
(defop
  GetVerticesByLabels [vertex labels]
  Known [labels] -> Vertex [vertex] HasLabels [vertex labels])
(defweight
  GetVerticesByLabels [op & rest] 100)
(deftask
  GetVerticesByLabels
  (let [[v l] bindings
        ^Indexer indexer (:indexer ^IPersistentMap ctx)
        ^String label (variables l)
        ^Iterator iterator (.verticesByLabelJava indexer label)]
    (->> (iterator-seq iterator)
         (map #(assoc variables v %)))))

(defop
  CheckLabels [vertex labels]
  Vertex [vertex] Known [labels] -> HasLabels [vertex labels])
(defweight
  CheckLabels [op & rest] 0.5)
(deftask
  CheckLabels
  (let [[v l] bindings
        ^IngraphVertex vertex (variables v)
        ^String label (variables l)]
    (if (.contains (.labels vertex) label) (list variables) ())))

(defop
  GetEdges [source edge target] -> DirectedEdge [source edge target])
(defweight
  GetEdges [op & rest] 1000)
(deftask
  GetEdges
  (let [[v e w] bindings
        ^Indexer indexer (:indexer ^IPersistentMap ctx)
        ^Iterator iterator (.edgesJava indexer)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     src (.sourceVertex edge)
                     tgt (.targetVertex edge)]
                 (assoc variables v src e edge w tgt))))))

(defop
  GetEdgesByType [source edge target type]
  Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  GetEdgesByType [op & rest] 100)
(deftask
  GetEdgesByType
  (let [[v e w t] bindings
        ^Indexer indexer (:indexer ^IPersistentMap ctx)
        ^String type (variables t)
        ^Iterator iterator (.edgesByTypeJava indexer type)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     src (.sourceVertex edge)
                     tgt (.targetVertex edge)]
                 (assoc variables v src e edge w tgt))))))

(defop
  CheckType [edge type]
  Edge [edge] Known [type] -> HasType [edge type])
(defweight
  CheckType [op & rest] 0.5)
(deftask
  CheckType
  (let [[e t] bindings
        ^IngraphEdge edge (variables e)
        ^String type (variables t)]
    (if (= (.type edge) type) (list variables) ())))

(defop
  AccessPropertyByKey [element key val]
  Element [element] Known [key] -> Property [element key val])
(defweight
  AccessPropertyByKey [op & rest] 1)
(deftask
  AccessPropertyByKey (let [[e k v]                 bindings
                            ^IngraphElement element (variables e)
                            ^String key             (variables k)
                            value                   (-> element (.properties) (.get key) (.getOrElse (proxy [AbstractFunction0] [] (apply [] nil))))]
                        (list (assoc variables v value))))

(defop
  ExtendOut [source edge target]
  Vertex [source] -> DirectedEdge [source edge target])
(defweight
  ExtendOut [op & rest] 10)
(deftask
  ExtendOut
  (let [[v e w] bindings
        ^IngraphVertex vertex (variables v)
        ^Iterator iterator (.edgesOutJavaIterator vertex)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex tgt (.targetVertex edge)]
                 (assoc variables e edge w tgt))))))

(defop
  ExtendIn [target edge source]
  Vertex [target] -> DirectedEdge [source edge target])
(defweight
  ExtendIn [op & rest] 10)
(deftask
  ExtendIn
  (let [[v e w] bindings
        ^IngraphVertex vertex (variables v)
        ^Iterator iterator (.edgesInJavaIterator vertex)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex src (.sourceVertex edge)]
                 (assoc variables e edge w src))))))

(defop
  ExtendOutByType [source edge target type]
  Vertex [source] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  ExtendOutByType [op & rest] 5)
(deftask
  ExtendOutByType
  (let [[v e w t] bindings
        ^IngraphVertex vertex (variables v)
        ^String type (variables t)
        ^Iterator iterator (.edgesOutByTypeJavaIterator vertex type)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex tgt (.targetVertex edge)]
                 (assoc variables e edge w tgt))))))

(defop
  ExtendInByType [target edge source type]
  Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  ExtendInByType [op & rest] 5)
(deftask
  ExtendInByType
  (let [[v e w t] bindings
        ^IngraphVertex vertex (variables v)
        ^String type (variables t)
        ^Iterator iterator (.edgesInByTypeJavaIterator vertex type)]
    (->> (iterator-seq iterator)
         (map #(let [^IngraphEdge edge %
                     ^IngraphVertex src (.sourceVertex edge)]
                 (assoc variables e edge w src))))))

(defop
  Join [source edge target]
  Vertex [source] Vertex [target] -> DirectedEdge [source edge target])
(defweight
  Join [op & rest] 0.5)
(deftask
  Join
  (let [[v e w] bindings
        ^IngraphVertex source (variables v)
        ^IngraphVertex target (variables w)
        ;; TODO No indexer support so we'll fall back to filtering :(
        ^Iterator iterator (.edgesInJavaIterator target)]
    (->> (iterator-seq iterator)
         ;; filter for matches and map to lookup in one function
         (mapcat #(let [^IngraphEdge edge %
                        ^IngraphVertex src (.sourceVertex edge)]
                    (if (= src source)
                      (list (assoc variables e edge v src))
                      ()))))))

(defop
  JoinByType [source edge target type]
  Vertex [source] Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  JoinByType [op & rest] 0.1)
(deftask
  JoinByType
  (let [[v e w t] bindings
        ^IngraphVertex source (variables v)
        ^IngraphVertex target (variables w)
        ^String type (variables t)
        ;; No indexer support so will fall back to filtering :(
        ^Iterator iterator (.edgesInByTypeJavaIterator target type)]
    (->> (iterator-seq iterator)
         ;; filter for matches and map to lookup in one function
         (mapcat #(let [^IngraphEdge edge %
                        ^IngraphVertex src (.sourceVertex edge)]
                    (if (= src source)
                      (list (assoc variables e edge v src))
                      ()))))))

;; Does this make any sense at all?
(defop
  CheckDirectedEdge [source edge target]
  Vertex [source] Edge [edge] Vertex [target] -> DirectedEdge [source edge target])
(defweight
  CheckDirectedEdge [op & rest] 0.5)
(deftask CheckDirectedEdge
  (let [[v e w] bindings
        ^IngraphVertex source (variables v)
        ^IngraphVertex target (variables w)
        ^IngraphEdge edge (variables e)]
    (if (and (= (.sourceVertex edge) source)
             (= (.targetVertex edge) target))
      (list variables)
      ())))

(defop
  CheckDirectedEdgeByType [source edge target type]
  Vertex [source] Edge [edge] Vertex [target] Known [type] -> DirectedEdge [source edge target] HasType [edge type])
(defweight
  CheckDirectedEdgeByType [op & rest] 0.1)
(deftask
  CheckDirectedEdgeByType
  (let [[v e w t] bindings
        ^IngraphVertex source (variables v)
        ^IngraphVertex target (variables w)
        ^IngraphEdge edge (variables e)
        ^String type (variables t)]
    (if (and (= (.sourceVertex edge) source)
             (= (.targetVertex edge) target)
             (= (.type edge) t))
      (list variables)
      ())))

(defop
  EvalGenUnaryAssertion [x cond]
  Known [x] Known [cond] -> GenUnaryAssertion [x cond])
(defweight
  EvalGenUnaryAssertion [op & rest] 0.1)
(deftask
  EvalGenUnaryAssertion
  (let [[x ucond] (map variables bindings)]
    (if (ucond x) (list variables) ())))


(defop
  EvalGenBinaryAssertion [x y cond]
  Known [x] Known [y] Known [cond] -> GenBinaryAssertion [x y cond])
(defweight
  EvalGenBinaryAssertion [op & rest] 0.1)
(deftask
  EvalGenBinaryAssertion
  (let [[x y bicond] (map variables bindings)]
    (if (bicond x y) (list variables) ())))

(defop
  BindConstant [x y]
  -> Constant [x y])
(defweight
  BindConstant [op & rest] 1)
(deftask
  BindConstant (let [[x y] bindings] (list (assoc variables x y))))
