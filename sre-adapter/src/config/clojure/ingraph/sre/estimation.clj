(ns ingraph.sre.estimation
  (:require [sre.plan.dsl.estimation :refer :all]
            [ingraph.sre.plan :refer :all]))

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

(defmethod weight #'GetVertices [op] 5)
(defmethod weight #'GetVerticesByLabels [op] 3)
(defmethod weight #'CheckLabels [op] 0.5)
(defmethod weight #'GetEdges [op] 5)
(defmethod weight #'GetEdgesByType [op] 3)
(defmethod weight #'AccessPropertyByKey [op] 1)
(defmethod weight #'CheckType [op] 0.5)
(defmethod weight #'ExtendOut [op] 3)
(defmethod weight #'ExtendIn [op] 3)
(defmethod weight #'ExtendOutByType [op] 2)
(defmethod weight #'ExtendInByType [op] 3)
(defmethod weight #'Join [op] 10)
(defmethod weight #'JoinByType [op] 5)
(defmethod weight #'CheckDirectedEdge [op] 0.5)
(defmethod weight #'CheckDirectedEdgeByType [op] 0.3)
(defmethod weight #'EvalGenBinaryAssertion [op] 0.1)
(defmethod weight #'EvalGenUnaryAssertion [op] 0.1)

(defn Estimation-createCostCalculator [^long c ^long p] (->CostCalculator c p))

(defn Estimation-createWeightCalculator [] (->WeightCalculator 0))

(gen-class :name ingraph.sre.estimation.Estimation
           :prefix "Estimation-"
           :main false
           :load-impl-ns true                               ; this is true by default
           :methods [^:static [createCostCalculator [long long] ingraph.sre.estimation.CostCalculator]
                     ^:static [createWeightCalculator [] ingraph.sre.estimation.WeightCalculator]])

