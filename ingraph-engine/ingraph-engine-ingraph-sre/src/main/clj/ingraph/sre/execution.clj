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
         (let [[v] (-> this :vars)]
           (map #(assoc var-lkp v %)
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^Iterator iterator (.verticesJava indexer)]
                (iterator-seq iterator)))))

; TODO should work with multiple labels, but no indexer support yet [#184]
(deftask GetVerticesByLabels
         #'plan/GetVerticesByLabels
         (let [[v l] (-> this :vars)]
           (map #(assoc var-lkp v %)
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^String label (var-lkp l)
                    ^Iterator iterator (.verticesByLabelJava indexer label)]
                (iterator-seq iterator)))))

; TODO should add support for multiple set of labels
(deftask CheckLabels
         #'plan/CheckLabels
         (let [[v l] (-> this :vars)
               ^IngraphVertex vertex (var-lkp v)
               ^String label (var-lkp l)]
           (if (.contains (.labels vertex) label) (list var-lkp) ())))

(deftask GetEdges
         #'plan/GetEdges
         (let [[v e w] (-> this :vars)]
           (map #(let [^IngraphEdge edge %
                     src (.sourceVertex edge)
                     tgt (.targetVertex edge)]
                 (assoc var-lkp v src e edge w tgt))
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^Iterator iterator (.edgesJava indexer)]
                (iterator-seq iterator)))))

(deftask GetEdgesByType #'plan/GetEdgesByType
         (let [[v e w t] (-> this :vars)]
           (map #(let [^IngraphEdge edge %
                     src (.sourceVertex edge)
                     tgt (.targetVertex edge)]
                 (assoc var-lkp v src e edge w tgt))
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^String type (var-lkp t)
                    ^Iterator iterator (.edgesByTypeJava indexer type)]
                (iterator-seq iterator)))))

(deftask CheckType
         #'plan/CheckType
         (let [[e t] (-> this :vars)
               ^IngraphEdge edge (var-lkp e)
               ^String type (var-lkp t)]
           (if (= (.type edge) type) (list var-lkp) ())))

(deftask ExtendOut
         #'plan/ExtendOut
         (let [[v e w] (-> this :vars)]
           (map #(let [^IngraphEdge edge %
                       ^IngraphVertex tgt (.targetVertex edge)]
                   (assoc var-lkp e edge w tgt))
                (let [^IngraphVertex vertex (var-lkp v)
                      ^Iterator iterator (.edgesOutJavaIterator vertex)]
                  (iterator-seq iterator)))))

(deftask ExtendIn
         #'plan/ExtendIn
         (let [[v e w] (-> this :vars)]
           (map #(let [^IngraphEdge edge %
                       ^IngraphVertex src (.sourceVertex edge)]
                   (assoc var-lkp e edge w src))
                (let [^IngraphVertex vertex (var-lkp v)
                      ^Iterator iterator (.edgesInJavaIterator vertex)]
                  (iterator-seq iterator)))))

(deftask ExtendOutByType #'plan/ExtendOutByType
         (let [[v e w t] (-> this :vars)]
           (map #(let [^IngraphEdge edge %
                       ^IngraphVertex tgt (.targetVertex edge)]
                   (assoc var-lkp e edge w tgt))
                (let [^IngraphVertex vertex (var-lkp v)
                      ^String type (var-lkp t)
                      ^Iterator iterator (.edgesOutByTypeJavaIterator vertex type)]
                  (iterator-seq iterator)))))

(deftask ExtendInByType #'plan/ExtendInByType
         (let [[v e w t] (-> this :vars)]
           (map #(let [^IngraphEdge edge %
                       ^IngraphVertex src (.sourceVertex edge)]
                   (assoc var-lkp e edge w src))
                (let [^IngraphVertex vertex (var-lkp v)
                      ^String type (var-lkp t)
                      ^Iterator iterator (.edgesInByTypeJavaIterator vertex type)]
                  (iterator-seq iterator)))))

(deftask Join #'plan/Join (???))

(deftask JoinByType #'plan/JoinByType (???))

(deftask CheckDirectedEdge #'plan/CheckDirectedEdge (???))

(deftask CheckDirectedEdgeByType #'plan/CheckDirectedEdgeByType (???))

(deftask EvalEquals #'plan/EvalEquals (???))



