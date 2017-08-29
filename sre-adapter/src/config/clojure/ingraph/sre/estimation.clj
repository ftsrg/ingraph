(ns ingraph.sre.estimation
  (:require [sre.plan.dsl.estimation :refer :all]
            [ingraph.sre.plan :as plan]))

(defmulti weight (fn [op & rest] (:name op)))

(defrecord CostCalculator [^long c ^long p]
           Cost
           (update-cost [this weight]
                        (as-> this this
                              (update-in this [:p] #(* %1 weight))
                              (update-in this [:c] #(+ %1 (:p this)))))
           Comparable
           (compareTo [this other] (compare (:c this) (:c other))))

(defrecord WeightCalculator [^long w]
           Weight
           (update-weight [this bound-op constraint-lookup]
                          (assoc-in this [:w] (weight bound-op)))
           (get-weight [this] w))

(defn ??? [] (throw (Exception. "Not implemented yet")))

(defmethod weight #'plan/GetVertices [op] 5)
(defmethod weight #'plan/GetVerticesByLabels [op] 3)
(defmethod weight #'plan/CheckLabels [op] 0.5)
(defmethod weight #'plan/GetEdges [op] 5)
(defmethod weight #'plan/GetEdgesByType [op] 3)
(defmethod weight #'plan/CheckType [op] 0.5)
(defmethod weight #'plan/ExtendOut [op] 3)
(defmethod weight #'plan/ExtendIn [op] 3)
(defmethod weight #'plan/ExtendOutByType [op] 2)
(defmethod weight #'plan/ExtendInByType [op] 3)
(defmethod weight #'plan/Join [op] 10)
(defmethod weight #'plan/JoinByType [op] 5)
(defmethod weight #'plan/CheckDirectedEdge [op] 0.5)
(defmethod weight #'plan/CheckDirectedEdgeByType [op] 0.3)
(defmethod weight #'plan/EvalGenBinaryAssertion [op] 0.1)
(defmethod weight #'plan/EvalGenUnaryAssertion [op] 0.1)

(defn Estimation-createCostCalculator [^long c ^long p] (->CostCalculator c p))

(defn Estimation-createWeightCalculator [] (->WeightCalculator 0))

(gen-class :name ingraph.sre.estimation.Estimation
           :prefix "Estimation-"
           :main false
           :load-impl-ns true                               ; this is true by default
           :methods [^:static [createCostCalculator [long long] ingraph.sre.estimation.CostCalculator]
                     ^:static [createWeightCalculator [] ingraph.sre.estimation.WeightCalculator]])

