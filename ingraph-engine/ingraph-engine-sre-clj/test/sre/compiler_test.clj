(ns sre.compiler-test
  (:require [clojure.test :refer :all]
            [clojure.set :refer :all]
            [sre.compiler :refer :all]
            [sre.constraint :refer [defconstraint bind implies*]]
            [sre.op :refer [defop]]))

(defn create-lkp [& args]
  (as-> (partition 2 args) x
        (map (fn [[n args]] (apply (partial bind n) args)) x)
        (into #{} x)
        (constr-set-to-constr-lkp x)))

(deftest test-compare-constraint-descriptors
  (testing "Compare contraint descriptors"
    (testing "should return positive for greater count"
      (is (pos?
            (compare-constraint-descriptors
              ['A {:n 3}]
              ['B {:n 1}])))

      (is (pos?
            (compare-constraint-descriptors
              ['A {:n 3 :arity 1}]
              ['B {:n 1 :arity 3}]))))

    (testing "should return positive for equal count and lesser arity"
      (is (pos?
            (compare-constraint-descriptors
              ['A {:n 1 :arity 3}]
              ['B {:n 1 :arity 5}]))))

    (testing "should differentiate on type"
      (is (not=
            (compare-constraint-descriptors
              ['A {:n 1 :arity 1}]
              ['B {:n 1 :arity 1}])
            0)))

    (testing "should differentiate on condition type"
      (is (not=
            (compare-constraint-descriptors
              ['A {:n 1 :arity 1 :cond :pre}]
              ['A {:n 1 :arity 1 :cond :post}])
            0)))))

(deftest test-branch-constr
  (testing "Branching"
    (testing "with no matching pre-condition"
      (testing "should return no branches"
        (let [stump {:var-lkp          {}
                     :bound-constr-lkp {'B [[1 2]]}
                     :free-constr-lkp  {'A [[1 2]]}
                     :sorted-constr    (list ['A {:n 1 :arity 1 :params [1 2] :cond :pre}])
                     }]
          (is (empty? (branch-constr stump))))))

    (testing "with no matching post-condition"
      (testing "should return no branches"
        (let [stump {:var-lkp          {}
                     :bound-constr-lkp {'A [[1 2]]}
                     :free-constr-lkp  {'B [[1 2]]}
                     :sorted-constr    (list ['A {:n 1 :arity 1 :params [1 2] :cond :post}])
                     }]
          (is (empty? (branch-constr stump))))))

    (testing "with matching pre-condition"
      (testing "and no conflict"
        (testing "should return a branch"
          (let [stump {:var-lkp          {}
                       :bound-constr-lkp {'A [[1 2]]}
                       :free-constr-lkp  {'B [[1 2]]}
                       :sorted-constr    (list ['A {:n 1 :arity 1 :params ['a 'b] :cond :pre}])
                       }
                branches (branch-constr stump)]
            (is (= (count branches) 1))
            (is (= (first branches)
                   {:var-lkp          {'a 1 'b 2}
                    :bound-constr-lkp {'A [[1 2]]}
                    :free-constr-lkp  {'B [[1 2]]}
                    :sorted-constr    nil}))))
        (testing "should return a branch and bind the post-conditions"
          (let [stump {:var-lkp          {}
                       :bound-constr-lkp {'A [[2 3]]}
                       :free-constr-lkp  {'A [[1 2]]}
                       :sorted-constr    (list ['A {:n 1 :arity 1 :params ['a 'b] :cond :post}])
                       }
                branches (branch-constr stump)]
            (is (= (count branches) 1))
            (is (= (first branches)
                   {:var-lkp          {'a 1 'b 2}
                    :bound-constr-lkp {'A [[1 2] [2 3]]}
                    :free-constr-lkp  {}
                    :sorted-constr    nil})))))

      (testing "and conflict"
        (testing "should return no branches"
          (let [stump {:var-lkp          {'a 2}
                       :bound-constr-lkp {'A [[1 2]]}
                       :free-constr-lkp  {'B [[1 2]]}
                       :sorted-constr    (list ['A {:n 1 :arity 1 :params ['a 'b] :cond :pre}])
                       }
                branches (branch-constr stump)]
            (is (empty? branches))))))))

(defconstraint TestConstraint02 [a])

(defconstraint TestConstraint03 [a b] :implies TestConstraint02 [a])


(defop TestOp01 [a b c]
       :requires TestConstraint03 [a b]
       :satisfies TestConstraint03 [b c])

(defop TestOp02 [a b]
       :requires TestConstraint02 [a] TestConstraint02 [b]
       :satisfies TestConstraint03 [a b])

(defop TestOp03 [a b]
  :satisfies TestConstraint03 [a b])

(deftest test-bind-op
  (testing "Binding"
    (testing "a non-satisfiable operation"
      (testing "should result in an empty binding list"
        (is (empty? (bind-op TestOp01
                             {}
                             {#'TestConstraint02 [[2]]
                              #'TestConstraint03 [[2 3]]})))
        (is (empty? (bind-op TestOp02
                             {#'TestConstraint02 [[1]]}
                             {#'TestConstraint03 [[1 2]]})))
        ))

    (testing "a non-required operation"
      (testing "should result in an empty binding list"
        (is (empty? (bind-op TestOp01
                             {#'TestConstraint03 [[1 2]]}
                             {})))))

    (testing "a satisfiable and required operation"
      (testing "should not be bound inconsistently (would overwrite binding of TestConstraint02)"
        (let [result (bind-op TestOp03
                              {#'TestConstraint02 [[1]]}
                              {#'TestConstraint03 [[1 2]]}
                              )]
          (is (empty? result))))
      (testing "should be bound every possible way"
        (testing "case #1: only one way"
          (let [result (bind-op TestOp01
                                {#'TestConstraint02 [[1]]
                                 #'TestConstraint03 [[1 2]]}
                                {#'TestConstraint02 [[2]]
                                 #'TestConstraint03 [[2 3]]})]
            (is (= 1 (count result)))
            (is (= (:var-lkp (first result)) {:a 1 :b 2 :c 3}))))
        (testing "case #2: two ways"
          (let [result (bind-op TestOp01
                                {#'TestConstraint02 [[1]]
                                 #'TestConstraint03 [[1 2] [1 3]]}
                                {#'TestConstraint02 [[2] [3]]
                                 #'TestConstraint03 [[2 3] [3 4]]})]
            (is (= 2 (count result)))
            (is (= (into #{} (map :var-lkp result)) #{{:a 1 :b 2 :c 3} {:a 1 :b 3 :c 4}}))))
        ))
    ))
