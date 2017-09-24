(ns sre.execution.executor-test
  (:require [clojure.test :refer :all]
            [clojure.zip :as z]
            [sre.execution.executor :refer :all]
            [sre.plan.task :refer :all]
            [sre.plan.config-2 :as c2]
            [clojure.pprint :refer :all])
  (:import [java.util Vector]))

(defrecord TestEnvironment [])

(deftest test-execution
  (let [env (->TestEnvironment)
        primitive-step (reify ISearch
                         (search [this state] (vector
                                                (->SearchTreeState {:a 1} (:env state))
                                                (->SearchTreeState {:a 2} (:env state)))))
        state (->SearchTreeState {} env)]
    (testing "PrimitiveTaskStep"
      (is (= (search primitive-step state) [(->SearchTreeState {:a 1} env)
                                            (->SearchTreeState {:a 2} env)])))
    (testing "ConjStep (PrimitiveTaskStep)"
      (let [conj-step (->ConjStep [primitive-step])]
        (is (= (lazy-seq (search conj-step state)) [(->SearchTreeState {:a 1} env)
                                                    (->SearchTreeState {:a 2} env)]))))
    (testing "ConjStep(ConjStep(PrimitiveTaskStep))"
      (let [conj-step (->ConjStep [(->ConjStep [primitive-step])])]
        (is (= (lazy-seq (search conj-step state)) [(->SearchTreeState {:a 1} env)
                                                    (->SearchTreeState {:a 2} env)]))))
    (testing "ConjStep(PrimitiveTaskStep PrimitiveTaskStep)"
      (let [primitive-step-2 (reify ISearch
                               (search [this state] [(->SearchTreeState (merge (:var-lkp state) {:b 2}) (:env state))]))
            conj-step (->ConjStep [primitive-step primitive-step-2])]
        (is (= (lazy-seq (search conj-step state))
               [(->SearchTreeState {:a 1 :b 2} env)
                (->SearchTreeState {:a 2 :b 2} env)]))))
    (testing "NotExistsStep(PrimitiveTaskStep)"
      (let [neg-step (->NotExistsStep primitive-step)]
        (is (empty? (search neg-step state)))))
    (testing "NotExistsStep(ConjStep(PrimitiveTaskStep PrimitiveTaskStep))"
      (let [primitive-step-2 (reify ISearch (search [this state] []))
            conj-step (->ConjStep [primitive-step primitive-step-2])
            neg-step (->NotExistsStep conj-step)
            state (->SearchTreeState {:c 5} env)]
        (is (= (lazy-seq (search neg-step state)) [state]))))
    (testing "UnionStep(PrimitiveTaskStep PrimitiveTaskStep)"
      (let [primitive-step-2 (reify ISearch
                               (search [this state] [(->SearchTreeState {:b 2} (:env state))]))
            union-step (->UnionStep [primitive-step primitive-step-2])]
        (is (= (into #{} (search union-step state)) #{(->SearchTreeState {:b 2} (:env state))
                                                      (->SearchTreeState {:a 1} (:env state))
                                                      (->SearchTreeState {:a 2} (:env state))}))))
    (testing "UnionStep(PrimitiveTaskStep NotExists(PrimitiveTaskStep))"
      (let [primitive-step-2 (reify ISearch
                               (search [this state] [(->SearchTreeState {:b 2} (:env state))]))
            union-step (->UnionStep [primitive-step (->NotExistsStep primitive-step-2)])]
        (is (= (into #{} (search union-step state)) #{(->SearchTreeState {:a 1} (:env state))
                                                      (->SearchTreeState {:a 2} (:env state))}))))
    (testing "UnionStep(PrimitiveTaskStep NotExists(PrimitiveTaskStep))"
      (let [primitive-step-2 (reify ISearch
                               (search [this state] [(->SearchTreeState {:b 2} (:env state))]))
            union-step (->UnionStep [primitive-step (->NotExistsStep primitive-step-2)])]
        (is (= (into #{} (search union-step state)) #{(->SearchTreeState {:a 1} (:env state))
                                                      (->SearchTreeState {:a 2} (:env state))}))))))
