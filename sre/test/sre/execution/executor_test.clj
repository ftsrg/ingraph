(ns sre.execution.executor-test
  (:require [clojure.test :refer :all]
            [clojure.zip :as z]
            [sre.execution.executor :refer :all]
            [sre.core :refer :all]
            [sre.plan.task :refer [ISearch search]]
            [sre.plan.config-2 :as c2]
            [clojure.pprint :refer :all])
  (:import [java.util Vector]))

(deftest test-execution
  (let [primitive-step (reify ISearch (search [this bindings variables ctx] [{:a 1} {:a 2}]))]
    (testing "PrimitiveTaskStep"
      (is (= (search primitive-step [:a] {} nil) [{:a 1} {:a 2}])))
    (testing "ConjStep (PrimitiveTaskStep)"
      (let [conj-step (->ConjStep [(->Binding primitive-step [:a])] [:a] [:a])]
        (is (= [{:b 1} {:b 2}]
               (lazy-seq (search conj-step [:b] {} nil))))))
    (testing "ConjStep(ConjStep(PrimitiveTaskStep))"
      (let [conj-step (->ConjStep [(->Binding (->ConjStep [(->Binding primitive-step
                                                                      [:a])]
                                                          [:a] [:a])
                                              [:b])]
                                  [:b] [:b])]
        (is (= (lazy-seq (search conj-step [:c] {} nil)) [{:c 1} {:c 2}]))))
    (testing "ConjStep(PrimitiveTaskStep PrimitiveTaskStep)"
      (let [primitive-step-2 (reify ISearch
                               (search [this bindings variables ctx] [(conj variables {:b 2})]))
            conj-step (->ConjStep [(->Binding primitive-step [:a :b])
                                   (->Binding primitive-step-2 [:a :b])] [:a :b] [:a :b])]
        (is (= (lazy-seq (search conj-step [:b :a] {} nil))
               [{:b 1 :a 2} {:b 2 :a 2}]))))
    (testing "NotExistsStep(PrimitiveTaskStep)"
      (let [neg-step (->NotExistsStep primitive-step)]
        (is (empty? (search neg-step [:a] {} nil)))))
    (testing "NotExistsStep(ConjStep(PrimitiveTaskStep PrimitiveTaskStep))"
      (let [primitive-step-2 (reify ISearch (search [this bindings variables ctx] []))
            conj-step (->ConjStep [(->Binding primitive-step [:a])
                                   (->Binding primitive-step-2 [:a :b])] [:a] [:a :b])
            neg-step (->NotExistsStep conj-step)
            variables {:c 5}]
        (is (= (lazy-seq (search neg-step [:c] variables nil)) [variables]))))
    ;(testing "UnionStep(PrimitiveTaskStep PrimitiveTaskStep)"
    ;  (let [primitive-step-2 (reify ISearch
    ;                           (search [this state] [(->State {:b 2} (:env state))]))
    ;        union-step (->UnionStep [primitive-step primitive-step-2])]
    ;    (is (= (into #{} (search union-step state)) #{(->State {:b 2} (:env state))
    ;                                                  (->State {:a 1} (:env state))
    ;                                                  (->State {:a 2} (:env state))}))))
    ;(testing "UnionStep(PrimitiveTaskStep NotExists(PrimitiveTaskStep))"
    ;  (let [primitive-step-2 (reify ISearch
    ;                           (search [this state] [(->State {:b 2} (:env state))]))
    ;        union-step (->UnionStep [primitive-step (->NotExistsStep primitive-step-2)])]
    ;    (is (= (into #{} (search union-step state)) #{(->State {:a 1} (:env state))
    ;                                                  (->State {:a 2} (:env state))}))))
    ;(testing "UnionStep(PrimitiveTaskStep NotExists(PrimitiveTaskStep))"
    ;  (let [primitive-step-2 (reify ISearch
    ;                           (search [this state] [(->State {:b 2} (:env state))]))
    ;        union-step (->UnionStep [primitive-step (->NotExistsStep primitive-step-2)])]
    ;    (is (= (into #{} (search union-step state)) #{(->State {:a 1} (:env state))
    ;                                                  (->State {:a 2} (:env state))}))))
    ))
