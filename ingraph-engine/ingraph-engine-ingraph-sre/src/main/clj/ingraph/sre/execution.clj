(ns ingraph.sre.execution
    (:require [sre.executor :refer :all]
              [ingraph.sre.plan :as plan])
    (:import [ingraph.ire Indexer]))

(defn ??? [] throw (Exception. "Not implemented yet"))

(defenv IngraphEnvironment [^Indexer indexer])

(deftask GetVertices #'plan/GetVertices (???))

(deftask GetVerticesByLabels #'plan/GetVerticesByLabel (???))

(deftask GetEdges #'plan/GetEdges (???))

(deftask GetEdgesByType #'plan/GetEdgesByType (???))

(deftask ExtendOut #'plan/ExtendOut (???))

(deftask ExtendIn #'plan/ExtendIn (???))

(deftask ExtendOutByType #'plan/ExtendOutByType (???))

(deftask ExtendInByType #'plan/ExtendInByType (???))

(deftask EvalEquals #'plan/EvalEquals (???))



