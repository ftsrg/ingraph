(ns sre.plan.lookup-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [sre.plan.config-1 :refer :all]
            [sre.plan.dsl.constraint :refer :all]
            [sre.plan.lookup :refer :all]))

(deftest test-lookup
  (let [free #{(->ConstraintBinding #'TestConstraint01 [1])
               (->ConstraintBinding #'TestConstraint02 [1 2])}
        bound #{(->ConstraintBinding #'TestConstraint01 [2])}
        lkp (constraint-lookup free bound)]
    (testing "Can query constraint-lookup"
      (is (= (lookup lkp :free) free))
      (is (= (get (lookup-by-type lkp :free) #'TestConstraint01) #{[1]}))
      (is (= (get (lookup-by-var lkp :free) 1) free))
      (is (= (get (lookup-by-var lkp :bound) 2) bound))
      (is (= (get (lookup-by-var lkp :free) 2) #{(->ConstraintBinding #'TestConstraint02 [1 2])})))
    (testing "Can move free to bound"
      (let [lkp (bind lkp (->ConstraintBinding #'TestConstraint02 [1 2]))]
        (is (= (lookup lkp :free) #{(->ConstraintBinding #'TestConstraint01 [1])}))
        (is (empty? (get (lookup-by-type lkp :free) #'TestConstraint02)))
        (is (= (get (lookup-by-type lkp :free) #'TestConstraint01) #{[1]}))
        (is (empty? (get (lookup-by-var lkp :free) 2)))
        (is (= (get (lookup-by-var lkp :free) 1) #{(->ConstraintBinding #'TestConstraint01 [1])}))
        (is (= (get (lookup-by-var lkp :bound) 1) #{(->ConstraintBinding #'TestConstraint02 [1 2])}))
        (is (= (get (lookup-by-var lkp :bound) 2) #{(->ConstraintBinding #'TestConstraint01 [2])
                                                    (->ConstraintBinding #'TestConstraint02 [1 2])}))))))
