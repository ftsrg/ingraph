(ns ingraph.sre.estimation
  (:require [sre.plan.config :as config]
            [sre.plan.estimation :refer :all]
            [ingraph.sre.config :refer :all]))

(def cost-calculator default-cost-calculator)

(def weight-calculator default-weight-calculator)

(defn Estimation-costCalculator [] cost-calculator)

(defn Estimation-weightCalculator [] weight-calculator)

(gen-class :name ingraph.sre.estimation.Estimation
           :prefix "Estimation-"
           :main false
           :methods [^:static [costCalculator [] sre.plan.estimation.Cost]
                     ^:static [weightCalculator [] sre.plan.estimation.Weight]])

