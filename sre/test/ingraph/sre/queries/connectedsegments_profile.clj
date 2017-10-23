(ns ingraph.sre.queries.connectedsegments-profile
  (:require [ingraph.sre.ingraph-proto-1 :refer [Ingraph]]
            [ingraph.sre.queries.connectedsegments-pattern :as cs]
            [ingraph.sre.queries.utils.tb-loader :as tb-loader]
            [ingraph.sre.queries.utils :as utils]
            [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte]))

(defn connected-segments-query [k] (pattern/compile cs/p Ingraph {:k k}))

(tufte/add-basic-println-handler! {})


(comment (doseq [k [1 5 10]]
           (tufte/profile {} (tufte/p (keyword (str "k-" k)) (connected-segments-query k)))))

(def trainbenchmark (tb-loader/load))

(def cs (connected-segments-query 1))

(utils/get-tasks cs)

(defn run-search [ctx]
  (pattern/run cs [:sensor :segment1 :segment2 :segment3 :segment4 :segment5 :segment6] ctx))
(run-search {:indexer trainbenchmark})
