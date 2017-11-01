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
    [this subcost]
    "Updates the cost in this calculator. Should return an instance that can be safely shared, ie. immutable."))

(defrecord CostCalculator [c p]
  Cost
  (update-cost [this subcost]
    (-> this
      (update-in [:p] #(* %1 (:p subcost)))
      (update-in [:c] #(+ %1 (* p (:c subcost))))))
  Comparable
  (compareTo [this other] (compare (:c this) (:c other))))

(def default-cost-calculator (->CostCalculator 0.0 1.0))

(defmacro defweight
  "Adds a weight estimator for the operation in the current config."
  [op args body]
  (if (contains? (ns-interns *ns*) '-costs)
    `(defmethod ~'-costs ~op ~args (let [weight# ~body]
                                       (->CostCalculator weight# weight#)))))
