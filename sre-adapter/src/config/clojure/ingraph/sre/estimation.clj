(ns ingraph.sre.estimation
  (:require [sre.plan.config :as config]
            [sre.plan.estimation :refer :all]
            [ingraph.sre.config :refer :all]))

(defrecord CostCalculator [^long c ^long p]
           Cost
           (update-cost [this weight]
                        (as-> this this
                              (update-in this [:p] #(* %1 weight))
                              (update-in this [:c] #(+ %1 (:p this)))))
           Comparable
           (compareTo [this other] (compare (:c this) (:c other))))

(def cost-calculator (->CostCalculator 0 1))

(def weight-calculator
  (reify Weight
    (get-weight [this op-binding constraint-lookup]
      ((config/weight Ingraph) op-binding))))

(defn Estimation-costCalculator [] cost-calculator)

(defn Estimation-weightCalculator [] weight-calculator)

(gen-class :name ingraph.sre.estimation.Estimation
           :prefix "Estimation-"
           :main false
           :methods [^:static [costCalculator [] sre.plan.estimation.Cost]
                     ^:static [weightCalculator [] sre.plan.estimation.Weight]])

