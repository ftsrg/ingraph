(ns sre.compiler-test
  (:require [clojure.test :refer :all]
            [clojure.set :refer :all]
            [sre.compiler :refer :all]
            [sre.constraint :refer [defconstraint bind implies*]]
            [sre.op :refer [defop]]
            [sre.op :as op]))

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
        (let [stump {:var-lkp {}
                     :todo    (list ['A {:n 1 :arity 1 :params [1 2] :cond [:requires :bound]}])
                     :done    ()
                     }]
          (is (empty? (branch-constr {:bound {'B #{[1 2]}}} stump))))))

    (testing "with no matching post-condition"
      (testing "should return no branches"
        (let [stump {:var-lkp {}
                     :todo    (list ['A {:n 1 :arity 1 :params [1 2] :cond [:satisfies :free]}])
                     :done    ()
                     }]
          (is (empty? (branch-constr {:free {'B #{[1 2]}} :bound {}} stump))))))

    (testing "with matching pre-condition"
      (testing "and no conflict"
        (testing "should return a branch"
          (let [stump {:var-lkp {}
                       :todo    (list ['A {:n 1 :arity 1 :params ['a 'b] :cond [:requires :bound]}])
                       :done    ()}
                branches (branch-constr {:bound {'A #{[1 2]}} :free {'B #{[1 2]}}} stump)]
            (is (= (count branches) 1))
            (is (= (first branches)
                   {:var-lkp {'a 1 'b 2}
                    :todo    nil
                    :done    (list [:bound 'A [1 2]])}))))
        (testing "should return a branch and bind the post-conditions"
          (let [stump {:var-lkp {}
                       :todo    (list ['A {:n 1 :arity 1 :params ['a 'b] :cond [:satisfies :free]}])
                       :done    ()}
                branches (branch-constr {:bound {'A #{[2 3]}}
                                         :free  {'A #{[1 2]}}}
                                        stump)]
            (is (= (count branches) 1))
            (is (= (first branches)
                   {:var-lkp {'a 1 'b 2}
                    :todo    nil
                    :done    (list [:free 'A [1 2]])})))))

      (testing "and conflict"
        (testing "should return no branches"
          (let [stump {:var-lkp {'a 2}
                       :todo    (list ['A {:n 1 :arity 1 :params ['a 'b] :cond [:requires :bound]}])
                       :done    ()}
                branches (branch-constr {
                                         :bound {'A #{[1 2]}}
                                         :free  {'B #{[1 2]}}}
                                        stump)]
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

(deftest test-bind-op1
  (testing "Binding"
    (testing "a non-required, non-satisfiable operation"
      (testing "should result in an empty binding list in the future"
        (is
          (empty?
            (bind-op
              TestOp01
              {}
              {:bound {#'TestConstraint03 #{[1 2]}}
               :free  {}}
              [:free]))))
      (testing "should result in an empty binding list in the present"
        (is
          (empty?
            (bind-op
              TestOp01
              {}
              {:bound {#'TestConstraint03 #{[1 2]}}
               :free  {}}
              [:free :bound])))))
    (testing "a required but non-satisfiable operation"
      (testing "should result in a binding list in the future"
        (let [result (bind-op
                       TestOp01
                       {}
                       {:bound {}
                        :free  {#'TestConstraint03 #{[1 2]} #'TestConstraint02 #{[1]}}}
                       [:free])]
          (is (= 1 (count result)))
          (is (= (first result)
                 (map->BindingBranchNode {:var-lkp {:b 1, :c 2}
                                          :op      TestOp01
                                          :todo    nil
                                          :done    (list [:free #'sre.compiler-test/TestConstraint03 [1 2]]
                                                         [:free #'sre.compiler-test/TestConstraint02 [1]])})))))
      (testing "should result in an empty binding list in the present"
        (let [result (bind-op TestOp01
                              {}
                              {:bound {}
                               :free  {#'TestConstraint03 #{[1 2]}
                                       #'TestConstraint02 #{[1]}}}
                              [:free :bound])]
          (is (empty? result))))
      (testing "should result in an empty binding list incrementally"
        (let [constr-lkp {:bound {}
                          :free  {#'TestConstraint03 #{[1 1]} #'TestConstraint02 #{[1]}}}
              step-1 (bind-op TestOp01
                              {}
                              constr-lkp
                              [:free])]
          (is (= 1 (count step-1)))
          (let [step-2 (bind-op TestOp01
                                (:var-lkp (first step-1))
                                constr-lkp
                                [:bound])]
            (is (empty? step-2))))))
    (testing "a required and satisfiable operation"
      (testing "should not be bound inconsistently"
        (let [result (bind-op TestOp03
                              {}
                              {:bound {#'TestConstraint02 #{[1]}} ; implied by the one below, should be free
                               :free  {#'TestConstraint03 #{[1 2]}}}
                              [:free])]
          (is (empty? result))))
      (testing "should be bound every possible way"
        (testing "case #1: only one way"
          (let [result (bind-op TestOp01
                                {}
                                {:bound {#'TestConstraint02 #{[1]}
                                         #'TestConstraint03 #{[1 2]}}
                                 :free  {#'TestConstraint02 #{[2]}
                                         #'TestConstraint03 #{[2 3]}}}
                                [:free :bound])]
            (is (= 1 (count result)))
            (is (= (:var-lkp (first result)) {:a 1 :b 2 :c 3}))))
        (testing "case #2: one way incrementally"
          (let [constr-lkp {:bound {#'TestConstraint02 #{[1]}
                                    #'TestConstraint03 #{[1 2]}}
                            :free  {#'TestConstraint02 #{[2]}
                                    #'TestConstraint03 #{[2 3]}}}
                step-1 (bind-op TestOp01
                                {}
                                constr-lkp
                                [:free])]
            (is (= 1 (count step-1)))
            (let [step-2 (bind-op TestOp01
                                  (:var-lkp (first step-1))
                                  constr-lkp
                                  [:bound])]
              (is (= 1 (count step-2)))
              (is (= (:var-lkp (first step-2)) {:a 1 :b 2 :c 3})))))))))

;(deftest test-search-plan-step
;  (testing "#1"
;    (let [ops   (op/bind TestOp01)
;          plans {3 (sorted-set-by
;                      compare-search-plan-cell-by-cost
;                      (->SearchPlanCell {:c 0 :p 1} ()))}])))

(deftest test-search-plan )
