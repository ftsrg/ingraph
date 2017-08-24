(ns ingraph.sre.estimation
  (:require [sre.plan.dsl.estimation :refer :all]
            [ingraph.sre.plan :as plan]))

(defmulti weight (fn [op] (:name op)))

(defrecord CostCalculator [^int c ^int p]
           Cost
           (update-cost [this weight]
                        (as-> this this
                              (update-in this [:p] #(* %1 weight))
                              (update-in this [:c] #(+ %1 (:p this)))))
           Comparable
           (compareTo [this other] (compare (:c this) (:c other))))

(defrecord WeightCalculator [^int w]
           Weight
           (update-weight [this bound-op constraint-lookup]
                          (assoc-in this [:w] (weight bound-op)))
           (get-weight [this] (:w this)))

(defn ??? [] (throw (Exception. "Not implemented yet")))

(defmethod weight #'plan/GetVertices [op constr-lkp state] (???))
(defmethod weight #'plan/GetVerticesByLabels [op constr-lkp state] (???))
(defmethod weight #'plan/CheckLabels [op constr-lkp state] (???))
(defmethod weight #'plan/GetEdges [op constr-lkp state] (???))
(defmethod weight #'plan/GetEdgesByType [op constr-lkp state] (???))
(defmethod weight #'plan/CheckType [op constr-lkp state] (???))
(defmethod weight #'plan/ExtendOut [op constr-lkp state] (???))
(defmethod weight #'plan/ExtendIn [op constr-lkp state] (???))
(defmethod weight #'plan/ExtendOutByType [op constr-lkp state] (???))
(defmethod weight #'plan/ExtendInByType [op constr-lkp state] (???))
(defmethod weight #'plan/Join [op constr-lkp state] (???))
(defmethod weight #'plan/JoinByType [op constr-lkp state] (???))
(defmethod weight #'plan/CheckDirectedEdge [op constr-lkp state] (???))
(defmethod weight #'plan/CheckDirectedEdgeByType [op constr-lkp state] (???))
(defmethod weight #'plan/EvalEquals [op constr-lkp state] (???))
