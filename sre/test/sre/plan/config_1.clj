(ns sre.plan.config-1
    (:require [sre.plan.config :refer [defconfig]]
              [sre.plan.constraint :refer [defconstraint]]
              [sre.plan.op :refer [defop]]))

(defconfig Config1)

(defconstraint TestConstraint01 [a])

(defconstraint TestConstraint02 [a b] < TestConstraint01 [a])

(defop TestOp01 [a b c] TestConstraint02 [a b] -> TestConstraint02 [b c])

(defop TestOp02 [a b] TestConstraint01 [a] TestConstraint01 [b] -> TestConstraint02 [a b])

(defop TestOp03 [a b] -> TestConstraint02 [a b])
