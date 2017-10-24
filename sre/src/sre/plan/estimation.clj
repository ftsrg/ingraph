(ns sre.plan.estimation
  (:require [sre.plan.config :as config]))

(comment
  ;; https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
  (def some-guy-told-me-to-use-this-as-epsilon-pls-dont-kill-me 1.19e-7)

  (defmacro halfway-sane-positive-float-compare [a b]
    `(let [d# (Math/abs (- ~a ~b))
           m# (if (> ~a ~b) ~a ~b)]
       (cond
         (<= d# (* m# some-guy-told-me-to-use-this-as-epsilon-pls-dont-kill-me)) 0
         (= ~a m#) 1
         :else -1))))

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

(defrecord CostCalculator [c p]
  Cost
  (update-cost [this weight]
    (as-> this t
      (update-in t [:p] #(* %1 weight))
      (update-in t [:c] #(+ %1 (:p t)))))
  (to-weight [this] c)
  Comparable
  (compareTo [this other] (compare (:c this) (:c other))))

(def default-cost-calculator (->CostCalculator 0.0 1.0))

(def default-weight-calculator
  (reify Weight
    (get-weight [this config op-binding constraint-lookup]
      ((config/weights config) (:type op-binding) (:bindings op-binding)))))

(defmacro defweight
  "Adds a weight estimator for the operation in the current config."
  [op args body]
  (if (contains? (ns-interns *ns*) '-weights)
    `(defmethod ~'-weights ~op ~args ~body)))
