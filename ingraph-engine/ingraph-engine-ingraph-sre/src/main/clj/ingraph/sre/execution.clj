(ns ingraph.sre.execution
  (:require [sre.execution.dsl :refer :all]
            [ingraph.sre.plan :as plan])
  (:import [ingraph.ire Indexer]
           (java.util Iterator)))

(set! *warn-on-reflection* true)

(defn ??? [] (throw (Exception. "Not implemented yet")))

(defenv IngraphEnvironment [^Indexer indexer])

(deftask GetVertices
         #'plan/GetVertices
         (map #(assoc var-lkp (-> this :vars first) %)
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^Iterator iterator (.verticesJava indexer)]
                (iterator-seq iterator))))

(deftask GetVerticesByLabels
         #'plan/GetVerticesByLabels
         (map #(assoc var-lkp (-> this :vars first) %)
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^Iterator iterator (.verticesByLabelJava indexer
                                                             (-> this :vars (nth 1) var-lkp))]
                (iterator-seq iterator))))

(deftask CheckLabels
         #'plan/CheckLabels
         (if (-> this :vars (nth 0) .hasLabel))
         (map #(assoc var-lkp (-> this :vars first) %)
              (let [^IngraphEnvironment env env
                    ^Indexer indexer (.indexer env)
                    ^Iterator iterator (.verticesByLabelJava indexer
                                                             (-> this :vars (nth 1) var-lkp))]
                (iterator-seq iterator))))

(deftask GetEdges #'plan/GetEdges (???))

(deftask GetEdgesByType #'plan/GetEdgesByType (???))

(deftask CheckType #'plan/CheckType (???))

(deftask ExtendOut #'plan/ExtendOut (???))

(deftask ExtendIn #'plan/ExtendIn (???))

(deftask ExtendOutByType #'plan/ExtendOutByType (???))

(deftask ExtendInByType #'plan/ExtendInByType (???))

(deftask Join #'plan/Join (???))

(deftask JoinByType #'plan/JoinByType (???))

(deftask CheckDirectedEdge #'plan/CheckDirectedEdge (???))

(deftask CheckDirectedEdgeByType #'plan/CheckDirectedEdgeByType (???))

(deftask EvalEquals #'plan/EvalEquals (???))



