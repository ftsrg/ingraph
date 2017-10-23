(ns ingraph.sre.queries.poslength-profile
  (:require [ingraph.sre.ingraph-proto-1 :refer :all]
            [ingraph.sre.queries
             [poslength-pattern :refer [p]]
             [utils :as utils]]
            [ingraph.sre.queries.utils.tb-loader :as tb-loader]
            [sre.plan.pattern :as pattern]))

(def trainbenchmark (tb-loader/load))
(def pl (pattern/compile p Ingraph {:k 5}))
(utils/get-tasks pl)

(defn run-search [ctx]
  (pattern/run pl [:segment :length] ctx))
#_(run-search {:indexer trainbenchmark})
