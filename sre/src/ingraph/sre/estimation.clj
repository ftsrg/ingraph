(ns ingraph.sre.estimation
  (:require [ingraph.sre.ingraph-proto-1 :refer :all]
            [sre.plan
             [config :as config]
             [estimation :refer :all]]))

(def cost-calculator default-cost-calculator)

(defn Estimation-costCalculator [] cost-calculator)

(gen-class :name ingraph.sre.estimation.Estimation
           :prefix "Estimation-"
           :main false
           :methods [^:static [costCalculator [] sre.plan.estimation.Cost]])
