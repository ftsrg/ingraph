(ns ingraph.sre.execution
  (:require [sre.execution.dsl :refer :all]
            [ingraph.sre.plan :as plan])
  (:import [ingraph.ire Indexer IngraphVertex IngraphEdge]
           [java.util Iterator]))

;; This file contains performance-critical code. General good advice: look out for reflection warnings
;; in the tests, add type hints when they occur.
(set! *warn-on-reflection* true)

(defn ??? [] (throw (Exception. "Not implemented yet")))

(defenv IngraphEnvironment [^Indexer indexer])

(deftask GetVertices
         #'plan/GetVertices
         (let [[v] (-> this :vars)
               ^IngraphEnvironment env env
               ^Indexer indexer (.indexer env)
               ^Iterator iterator (.verticesJava indexer)]
           (->> (iterator-seq iterator)
                (map #(assoc var-lkp v %)))))

; TODO should work with multiple labels, but no indexer support yet [#184]
(deftask GetVerticesByLabels
         #'plan/GetVerticesByLabels
         (let [[v l] (-> this :vars)
               ^IngraphEnvironment env env
               ^Indexer indexer (.indexer env)
               ^String label (var-lkp l)
               ^Iterator iterator (.verticesByLabelJava indexer label)]
           (->> (iterator-seq iterator)
                (map #(assoc var-lkp v %)))))

; TODO should add support for multiple set of labels
(deftask CheckLabels
         #'plan/CheckLabels
         (let [[v l] (-> this :vars)
               ^IngraphVertex vertex (var-lkp v)
               ^String label (var-lkp l)]
           (if (.contains (.labels vertex) label) (list var-lkp) ())))

(deftask GetEdges
         #'plan/GetEdges
         (let [[v e w] (-> this :vars)
               ^IngraphEnvironment env env
               ^Indexer indexer (.indexer env)
               ^Iterator iterator (.edgesJava indexer)]
           (->> (iterator-seq iterator)
                (map #(let [^IngraphEdge edge %
                            src (.sourceVertex edge)
                            tgt (.targetVertex edge)]
                        (assoc var-lkp v src e edge w tgt))))))

(deftask GetEdgesByType
         #'plan/GetEdgesByType
         (let [[v e w t] (-> this :vars)
               ^IngraphEnvironment env env
               ^Indexer indexer (.indexer env)
               ^String type (var-lkp t)
               ^Iterator iterator (.edgesByTypeJava indexer type)]
           (->> (iterator-seq iterator)
                (map #(let [^IngraphEdge edge %
                            src (.sourceVertex edge)
                            tgt (.targetVertex edge)]
                        (assoc var-lkp v src e edge w tgt))))))

(deftask CheckType
         #'plan/CheckType
         (let [[e t] (-> this :vars)
               ^IngraphEdge edge (var-lkp e)
               ^String type (var-lkp t)]
           (if (= (.type edge) type) (list var-lkp) ())))

(deftask ExtendOut
         #'plan/ExtendOut
         (let [[v e w] (-> this :vars)
               ^IngraphVertex vertex (var-lkp v)
               ^Iterator iterator (.edgesOutJavaIterator vertex)]
           (->> (iterator-seq iterator)
                (map #(let [^IngraphEdge edge %
                            ^IngraphVertex tgt (.targetVertex edge)]
                        (assoc var-lkp e edge w tgt))))))

(deftask ExtendIn
         #'plan/ExtendIn
         (let [[v e w] (-> this :vars)
               ^IngraphVertex vertex (var-lkp v)
               ^Iterator iterator (.edgesInJavaIterator vertex)]
           (->> (iterator-seq iterator)
                (map #(let [^IngraphEdge edge %
                            ^IngraphVertex src (.sourceVertex edge)]
                        (assoc var-lkp e edge w src))))))

(deftask ExtendOutByType #'plan/ExtendOutByType
         (let [[v e w t] (-> this :vars)
               ^IngraphVertex vertex (var-lkp v)
               ^String type (var-lkp t)
               ^Iterator iterator (.edgesOutByTypeJavaIterator vertex type)]
           (->> (iterator-seq iterator)
                (map #(let [^IngraphEdge edge %
                            ^IngraphVertex tgt (.targetVertex edge)]
                        (assoc var-lkp e edge w tgt))))))

(deftask ExtendInByType #'plan/ExtendInByType
         (let [[v e w t] (-> this :vars)
               ^IngraphVertex vertex (var-lkp v)
               ^String type (var-lkp t)
               ^Iterator iterator (.edgesInByTypeJavaIterator vertex type)]
           (->> (iterator-seq iterator)
                (map #(let [^IngraphEdge edge %
                            ^IngraphVertex src (.sourceVertex edge)]
                        (assoc var-lkp e edge w src))))))

(deftask Join
         #'plan/Join
         (let [[v e w] (-> this :vars)
               ^IngraphVertex source (var-lkp v)
               ^IngraphVertex target (var-lkp w)
               ; No indexer support so will fall back to filtering :(
               ^Iterator iterator (.edgesInJavaIterator target)]
           (->> (iterator-seq iterator)
                ; filter for matches and map to lookup in one function
                (mapcat #(let [^IngraphEdge edge %
                               ^IngraphVertex src (.sourceVertex edge)]
                        (if (= src source)
                          (list (assoc var-lkp e edge v src))
                          ()))))))

(deftask JoinByType
         #'plan/JoinByType
         (let [[v e w t] (-> this :vars)
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

; I think these two doesn't really make sense
(deftask CheckDirectedEdge
         #'plan/CheckDirectedEdge
         (let [[v e w] (-> this :vars)
               ^IngraphVertex source (var-lkp v)
               ^IngraphVertex target (var-lkp w)
               ^IngraphEdge edge (var-lkp e)]
           (if (and (= (.sourceVertex edge) source)
                    (= (.targetVertex edge) target))
             (list var-lkp)
             ())))

(deftask CheckDirectedEdgeByType
         #'plan/CheckDirectedEdgeByType
         (let [[v e w t] (-> this :vars)
               ^IngraphVertex source (var-lkp v)
               ^IngraphVertex target (var-lkp w)
               ^IngraphEdge edge (var-lkp e)
               ^String type (var-lkp t)]
           (if (and (= (.sourceVertex edge) source)
                    (= (.targetVertex edge) target)
                    (= (.type edge) t))
             (list var-lkp)
             ())))

(deftask EvalEquals
         #'plan/EvalEquals
         (let [[a b] (map var-lkp (-> this :vars))]
           (if (= a b) (list var-lkp) ())))



