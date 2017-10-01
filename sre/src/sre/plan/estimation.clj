(ns sre.plan.estimation
  (:require [sre.plan.config :as config]))

(defprotocol Cost
  "Costs are assigned to search plans."
  (update-cost
    [this weight]
    "Updates the cost in this calculator. Should return an instance that can be safely shared, ie. immutable.")
  (to-weight [this] "Converts cost to a weight."))

(defprotocol Weight
  "Weights are assigned to operations."
  (get-weight
    [this config op-binding constraint-lkp]
    "Should return a weight that can be passed to update-cost"))

(defrecord CostCalculator [^long c ^long p]
  Cost
  (update-cost [this weight]
    (as-> this this
          (update-in this [:p] #(* %1 weight))
          (update-in this [:c] #(+ %1 (:p this)))))
  (to-weight [this] c)
  Comparable
  (compareTo [this other] (compare (:c this) (:c other))))

(def default-cost-calculator (->CostCalculator 0 1))

(def default-weight-calculator
  (reify Weight
    (get-weight [this config op-binding constraint-lookup]
      ((config/weights config) (:type op-binding) (:bindings op-binding)))))

(defmacro defweight
  "Adds a weight estimator for the operation in the current config."
  [op args body]
  (if (contains? (ns-interns *ns*) '-weights)
    `(defmethod ~'-weights ~op ~args ~body)))
