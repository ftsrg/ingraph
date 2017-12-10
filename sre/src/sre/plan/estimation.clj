(ns sre.plan.estimation
  (:require [sre.core :as core]))

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
  (calculate [this config op-binding constr-lkp] "Calculates cost of operation")
  (append
    [this subcost]
    "Appends the cost to this calculator. Should return an instance that can be safely shared, ie. immutable."))

(defmulti init-cost-calculator (fn [-> & rest] ->))

(defmacro defweight
  "Adds a weight estimator for the operation in the current config."
  [op args & body]
  (if (contains? (ns-interns *ns*) '-costs)
    `(defmethod ~'-costs ~op ~args
       (let [res# (do ~@body)] (->SimpleCostCalculator res# res#)))))


(defrecord SimpleCostCalculator [c p]
  Cost
  (calculate [this config op-binding constr-lkp]
    ((core/costs config) (:type op-binding) (:bindings op-binding) constr-lkp))
  (append [this subcost]
    (-> this
      (update-in [:p] #(* %1 (:p subcost)))
      (update-in [:c] #(+ %1 (* p (:c subcost))))))
  Comparable
  (compareTo [this other] (compare (:c this) (:c other))))

;; estimation-ctx is similar to runtime-ctx, but instead of getting search capabilites, you get estimation capabilities
;; from the underlying platform. It should not do side effects, like just like runtime-ctx.
;; IRL we use the same indexer for both purposes, but might make sense splitting up later.

(defrecord VarStatsCostCalculator [c p estimation-ctx est-var-cardinality]
  Cost
  (calculate [this config op-binding constr-lkp]
    ((core/costs config) (:type op-binding) (:bindings op-binding) estimation-ctx est-var-cardinality constr-lkp))
  (append [this subcost]
    (-> this
        (update-in [:p] #(* % (:p subcost)))
        (update-in [:c] #(+ % (* p (:c subcost))))
        (update-in [:est-var-cardinality] #(merge % (:est-var-cardinality subcost)))))
  Comparable
  (compareTo [this other] (compare (:c this) (:c other))))

(def default-cost-calculator ->SimpleCostCalculator)

(defmethod init-cost-calculator ->SimpleCostCalculator [->cc & _] (->cc 0.0 1.0))

(defmethod init-cost-calculator ->VarStatsCostCalculator [->cc estimation-ctx & _]
  (->cc 0.0 1.0 estimation-ctx {}))
