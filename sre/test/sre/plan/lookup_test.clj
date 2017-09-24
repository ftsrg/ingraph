(ns sre.plan.lookup-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [sre.plan.config-1 :refer :all]
            [sre.plan.constraint :as c]
            [sre.plan.lookup :refer :all]))

(deftest test-lookup
  (let [free #{(c/->ConstraintBinding TestConstraint01 [1])
               (c/->ConstraintBinding TestConstraint02 [1 2])}
        bound #{(c/->ConstraintBinding TestConstraint01 [2])}
        lkp (constraint-lookup free bound)]
    (testing "Can query constraint-lookup"
      (is (= (lookup lkp :free) free))
      (is (= (get (lookup-by-type lkp :free) TestConstraint01) #{[1]}))
      (is (= (get (lookup-by-var lkp :free) 1) free))
      (is (= (get (lookup-by-var lkp :bound) 2) bound))
      (is (= (get (lookup-by-var lkp :free) 2) #{(c/->ConstraintBinding TestConstraint02 [1 2])})))
    (testing "Can move free to bound"
      (let [lkp (bind lkp (c/->ConstraintBinding TestConstraint02 [1 2]))]
        (is (= (lookup lkp :free) #{(c/->ConstraintBinding TestConstraint01 [1])}))
        (is (empty? (get (lookup-by-type lkp :free) TestConstraint02)))
        (is (= (get (lookup-by-type lkp :free) TestConstraint01) #{[1]}))
        (is (empty? (get (lookup-by-var lkp :free) 2)))
        (is (= (get (lookup-by-var lkp :free) 1) #{(c/->ConstraintBinding TestConstraint01 [1])}))
        (is (= (get (lookup-by-var lkp :bound) 1) #{(c/->ConstraintBinding TestConstraint02 [1 2])}))
        (is (= (get (lookup-by-var lkp :bound) 2) #{(c/->ConstraintBinding TestConstraint01 [2])
                                                    (c/->ConstraintBinding TestConstraint02 [1 2])})))
      (let [mod (bind* lkp (c/->ConstraintBinding TestConstraint02 [1 2]))]
        (is (= (lookup mod :free) #{}))
        (is (= (lookup mod :bound) #{(c/->ConstraintBinding TestConstraint01 [2])
                                     (c/->ConstraintBinding TestConstraint01 [1])
                                     (c/->ConstraintBinding TestConstraint02 [1 2])}))))
    (testing "Can add/move constraints"
      (let [mod (with lkp :bound (c/->ConstraintBinding TestConstraint01 [3]))]
        (is (= (lookup mod :bound) #{(c/->ConstraintBinding TestConstraint01 [2])
                                     (c/->ConstraintBinding TestConstraint01 [3])}))
        (is (= (lookup mod :free) (lookup lkp :free))))
      (let [mod (with lkp :free (c/->ConstraintBinding TestConstraint01 [2]))]
        (is (= (lookup mod :bound) #{}))
        (is (= (lookup mod :free) (conj
                                    (lookup lkp :free)
                                    (c/->ConstraintBinding TestConstraint01 [2]))))))))
