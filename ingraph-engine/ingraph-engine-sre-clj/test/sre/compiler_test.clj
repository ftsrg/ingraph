(ns sre.compiler-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [sre.compiler :refer :all]
            [sre.constraint :refer [defconstraint]]
            [sre.op :refer [defop]]))

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
    (testing "with no matching precondition"
      (testing "should return no branches"
        (let [stump {:var-lkp          {}
                     :sat-constr-lkp   {'B [[1 2]]}
                     :unsat-constr-lkp {'A [[1 2]]}
                     :sorted-constr    #{['A {:n 1 :arity 1 :params [1 2] :cond :pre}]}
                     }]
          (is (empty? (branch-constr stump))))))

    (testing "with no matching postcondition"
      (testing "should return no branches"
        (let [stump {:var-lkp          {}
                     :sat-constr-lkp   {'A [[1 2]]}
                     :unsat-constr-lkp {'B [[1 2]]}
                     :sorted-constr    #{['A {:n 1 :arity 1 :params [1 2] :cond :post}]}
                     }]
          (is (empty? (branch-constr stump))))))

    (testing "with matching precondition"
      (testing "and no conflict"
           (testing "should return a branch"
             (let [stump {:var-lkp          {}
                          :sat-constr-lkp   {'A [[1 2]]}
                          :unsat-constr-lkp {'B [[1 2]]}
                          :sorted-constr    #{['A {:n 1 :arity 1 :params ['a 'b] :cond :pre}]}
                          }
                   branches (branch-constr stump)]
               (is (= (count branches) 1))
               (is (= (first branches)
                      {:var-lkp          {'a 1, 'b 2},
                       :sat-constr-lkp   {},
                       :unsat-constr-lkp {'B [[1 2]]},
                       :sorted-constr    #{}})))))

      (testing "and conflict"
        (testing "should return no branches"
          (let [stump {:var-lkp          {'a 2}
                       :sat-constr-lkp   {'A [[1 2]]}
                       :unsat-constr-lkp {'B [[1 2]]}
                       :sorted-constr    #{['A {:n 1 :arity 1 :params ['a 'b] :cond :pre}]}
                       }
                branches (branch-constr stump)]
            (is (empty? branches))))))))

(defconstraint TestConstraint [a b])

(defop TestOp [a b c]
       :requires TestConstraint [a b]
       :satisfies TestConstraint [b c])

(deftest test-bind-op
  (testing "Binding"
    (testing "a non-satisfiable operation"
      (testing "should result an empty binding list"
        (is (empty? (bind-op TestOp {} {#'TestConstraint [[2 3]]})))))
    (testing "a restricting operation"
      (testing "should result an empty binding list"
        (is (empty? (bind-op TestOp {#'TestConstraint [[1 2]]} {})))))
    (testing "a satisfiable and required operation"
      (testing "should be bound every possible way"
        (testing "case #1: only one way"
          (let [result (bind-op TestOp {#'TestConstraint [[1 2]]} {#'TestConstraint [[2 3]]})]
            (is (= 1 (count result)))
            (is (= (:var-lkp (first result)) {:a 1 :b 2 :c 3}))))
        (testing "case #2: two ways"
          (let [result (bind-op TestOp {#'TestConstraint [[1 2] [1 3]]} {#'TestConstraint [[2 3] [3 4]]})]
            (is (= 2 (count result)))
            (is (= (into #{} (map :var-lkp result)) #{{:a 1 :b 2 :c 3} {:a 1 :b 3 :c 4}}))))))))
