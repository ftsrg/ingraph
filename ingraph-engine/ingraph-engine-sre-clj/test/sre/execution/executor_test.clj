(ns sre.execution.executor-test
  (:require [clojure.test :refer :all]
            [clojure.zip :as z]
            [sre.execution.executor :refer :all]
            [sre.execution.dsl :refer :all]
            [sre.plan.config-2 :as c2]
            [clojure.pprint :refer :all])
  (:import [java.util Vector]))

(defmulti call-task-mock (fn [task & rest] (:name task)))

(defmacro with-methods
  "Makes it easier to define methods temporarily."
  [bindings & body]
  (let [methods (partition 4 bindings)]
    `(do ~@(for [[multi disp params body] methods] `(defmethod ~multi ~disp ~params ~body))
         ~@body
         ~@(for [[multi disp] (reverse methods)] `(remove-method ~multi ~disp)))))


(deftype TestEnvironment []
  Environment
  (create-task [this op] op)
  (call-task [this task var-lkp] (call-task-mock task var-lkp)))

(def simple-plan-1 (list {:requires  {},
                          :satisfies {#'c2/Vertex       [[3] [1]],
                                      #'c2/DirectedEdge [[1 2 3]],
                                      #'c2/Edge         [[2]],
                                      #'c2/Element      [[3] [1] [2]]},
                          :name      #'c2/GetEdges,
                          :vars      [1 2 3]}))

(def still-not-too-complicated-plan-1 (list {:requires  {},
                                             :satisfies {#'c2/Vertex       [[3] [1]],
                                                         #'c2/DirectedEdge [[1 2 3]],
                                                         #'c2/Edge         [[2]],
                                                         #'c2/Element      [[3] [1] [2]]},
                                             :name      #'c2/GetEdges,
                                             :vars      [1 2 3]}
                                            {:requires {#'c2/Element [[3]], #'c2/Vertex [[3]]},
                                             :satisfies {#'c2/Element [[4] [5]],
                                                         #'c2/DirectedEdge [[3 4 5]],
                                                         #'c2/Vertex [[5]],
                                                         #'c2/Edge [[4]]},
                                             :name #'c2/ExtendOut,
                                             :vars [3 4 5]}))

(deftest test-search-tree
  (testing "([GetEdges 1 2 3]) with 2 satisfying branches"
    (let [root (->SearchTreeNode #{} simple-plan-1)
          st (search-tree-zipper root (TestEnvironment.))
          branch-1 {1 :test-vertex-v,
                    2 :test-edge-e,
                    3 :test-vertex-w}
          branch-2 {1 :test-vertex-x
                    2 :test-edge-f
                    3 :test-vertex-y}]
      (with-methods [call-task-mock #'c2/GetEdges [& _] [branch-1 branch-2]]
                    (is (= (-> st z/down z/node) (->SearchTreeNode branch-1 nil)))
                    (is (= (-> st z/down z/right z/node) (->SearchTreeNode branch-2 nil))))))
  (testing "([GetEdges 1 2 3]) unsatisfiable"
    (let [root (->SearchTreeNode #{} simple-plan-1)
          st (search-tree-zipper root (TestEnvironment.))]
      (with-methods [call-task-mock #'c2/GetEdges [& _] []]
                    (is (-> st z/down z/node nil?)))))
  (testing "([GetEdges 1 2 3][ExtendOut 3 4 5] satisfying chain"
    (let [root (->SearchTreeNode #{} still-not-too-complicated-plan-1)
          st (search-tree-zipper root (TestEnvironment.))
          step-1 {1 :test-vertex-v,
                  2 :test-edge-e,
                  3 :test-vertex-w}
          step-2 (merge step-1
                        {4 :test-edge-f,
                         5 :test-vertex-q})]
      (with-methods [call-task-mock #'c2/GetEdges [& _] [step-1]
                     call-task-mock #'c2/ExtendOut [& _] [step-2]]
                    (is (= (-> st z/down z/down z/node) (->SearchTreeNode step-2 nil)))))))

(deftest test-search-executor
  (testing "()"
    (testing "#1"
      (let [result (execute () {} [] (TestEnvironment.))]
        (is (= (.size result) 0))))
    (testing "#2"
      (let [result (execute () {1 "dummy"} [1] (TestEnvironment.))]
        (is (= (.size result) 1))
        (is (= (aget (.elementAt result 0) 0) "dummy")))))
  (testing "(GetEdges [1 2 3])"
    (testing "with two satisfiable branches"
      (let [branch-1 {1 :test-vertex-v,
                      2 :test-edge-e,
                      3 :test-vertex-w}
            branch-2 {1 :test-vertex-x
                      2 :test-edge-f
                      3 :test-vertex-y}]
        (with-methods [call-task-mock #'c2/GetEdges [& _] [branch-1 branch-2]]
                      (let [result (execute simple-plan-1 {} [1 2 3] (TestEnvironment.))]
                        (is (= (.size result) 2))
                        (is (= (vec (.elementAt result 0)) [:test-vertex-v :test-edge-e :test-vertex-w]))
                        (is (= (vec (.elementAt result 1)) [:test-vertex-x :test-edge-f :test-vertex-y]))))))
    (testing "unsatisfiable"
      (with-methods [call-task-mock #'c2/GetEdges [& _] []]
                    (let [result (execute simple-plan-1 {} [1 2 3] (TestEnvironment.))]
                      (is (= (.size result) 0)))))))
