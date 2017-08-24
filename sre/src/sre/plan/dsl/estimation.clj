(ns sre.plan.dsl.estimation)

(defprotocol Cost
  (update-cost
    [this weight]
    "Updates the cost in this calculator. Should return an instance that can be safely shared, ie. immutable."))

(defprotocol Weight
  "Weights are calculated per operation in the algorithm. They are used to update costs."
  (update-weight
    [this bound-op constraint-lookup]
    "Update the weight in this calculator. Should return an instance that can be safely shared, ie. immutable")
  (get-weight
    [this]
    "Should return a weight that can be passed to update-cost"))
