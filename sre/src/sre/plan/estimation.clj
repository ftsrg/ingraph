(ns sre.plan.estimation)

(defprotocol Cost
  (update-cost
    [this weight]
    "Updates the cost in this calculator. Should return an instance that can be safely shared, ie. immutable."))

(defprotocol Weight
  "Weights are calculated per operation in the algorithm. They are used to update costs."
  (get-weight
    [this op-binding constraint-lkp]
    "Should return a weight that can be passed to update-cost"))

(defmacro defweight
  "Adds a weight estimator for the operation in the current config."
  [op args body]
  (if (contains? (ns-interns *ns*) '-weight)
    `(defmethod ~'-weight ~op ~args ~body)))
