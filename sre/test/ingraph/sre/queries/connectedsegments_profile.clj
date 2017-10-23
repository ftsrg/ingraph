(ns ingraph.sre.queries.connectedsegments-profile
  (:require [ingraph.sre.ingraph-proto-1 :refer [Ingraph]]
            [ingraph.sre.queries.connectedsegments-pattern :as cs]
            [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte]))

(defn connected-segments-query [k] (pattern/compile cs/p Ingraph {:k k}))

(tufte/add-basic-println-handler! {})


(doseq [k [1 5 10]]
  (tufte/profile {} (tufte/p (keyword (str "k-" k)) (connected-segments-query k))))
