(ns ingraph.sre.queries.poslength-profile
  (:require [ingraph.sre.ingraph-proto-1 :refer :all]
            [ingraph.sre.queries
             [poslength-pattern :refer [p]]
             [utils :as utils]]
            [ingraph.sre.queries.utils.tb-loader :as tb-loader]
            [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte]))

(tufte/add-basic-println-handler! {})
(def pl (pattern/compile p Ingraph {:k 5}))

(utils/get-tasks pl)

(defn run-search [ctx]
  (pattern/run pl [:segment :length] ctx))


(def inject-1 (tb-loader/load 'inject-1))

(defn run-profile [ctx]
  (tufte/profile {} (dotimes [_ 100]
                      (tufte/p :poslength-1 (run-search {:indexer ctx})))))
(comment (run-profile inject-1))
