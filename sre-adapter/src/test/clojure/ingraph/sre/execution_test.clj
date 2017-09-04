(ns ingraph.sre.execution-test
  (:require [clojure.test :refer :all]
            [sre.execution.dsl :refer :all]
            [ingraph.sre.execution :refer :all]
            [clojure.pprint :refer :all])
  (:import [ingraph.ire Indexer IngraphVertex IngraphEdge]
           [ingraph.sre.execution IngraphEnvironment]
           [scala.collection.immutable HashSet HashMap]
           [scala Tuple2]))

(set! *warn-on-reflection* true)

(def ^IngraphVertex person0
  (IngraphVertex. 0
                  (-> (HashSet.)
                      (.$plus "person"))
                  (-> (HashMap.)
                      (.$plus (Tuple2. "name" "Andy"))
                      (.$plus (Tuple2. "age" 24)))))
(def ^IngraphVertex person1
  (IngraphVertex. 1
                  (-> (HashSet.)
                      (.$plus "person"))
                  (-> (HashMap.)
                      (.$plus (Tuple2. "name" "Jessica"))
                      (.$plus (Tuple2. "age" 21)))))

(def ^IngraphVertex person2
  (IngraphVertex. 2
                  (-> (HashSet.)
                      (.$plus "person"))
                  (-> (HashMap.)
                      (.$plus (Tuple2. "name" "Charlie"))
                      (.$plus (Tuple2. "age" 35)))))

(def ^IngraphEdge p0-knows-p1
  (IngraphEdge. 3
                person0
                person1
                "knows"
                (-> (HashMap.)
                    (.$plus (Tuple2. "since" 2016)))))

(def ^IngraphEdge p1-knows-p0
  (IngraphEdge. 4
                person1
                person0
                "knows"
                (-> (HashMap.)
                    (.$plus (Tuple2. "since" 2016)))))

(def ^IngraphEdge p0-ch-p2
  (IngraphEdge. 5
                person0
                person2
                "child"
                (-> (HashMap.))))

;; Damn, we create an indexer for applying the f*ckin side effects of wiring the stuff
;; together. Gotta love imperative code.
(def ^Indexer indexer (Indexer.))

(doto indexer
  (.addVertex person0)
  (.addVertex person1)
  (.addVertex person2)
  (.addEdge p0-knows-p1)
  (.addEdge p1-knows-p0)
  (.addEdge p0-ch-p2))

;; You can recreate the Indexer in your test to retain some flexibility. Just remember not to change any relations
;; between the elements (do not add new stuff below this point), and that the elements
;; themselves hold state about others too. So nasty.

(deftest test-get-vertices
  (testing "GetVertices"
    (testing "should return nothing"
      (let [indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->GetVertices [7])]
        (is (empty? (call task env {})))))

    (testing "should return vertices"
      (let [env (IngraphEnvironment. indexer)
            task (->GetVertices [7])]
        (is (= #{{7 person0} {7 person1} {7 person2}}
               (into #{} (call task env {}))))))))

(deftest test-get-vertices-by-labels
  (testing "GetVerticesByLabels"
    (testing "should return nothing"
      (let [env (IngraphEnvironment. indexer)
            task (->GetVerticesByLabels [7 3])]
        (is (empty? (call task env {3 "movie"})))))

    (testing "should return vertices"
      (let [env (IngraphEnvironment. indexer)
            task (->GetVerticesByLabels [7 3])]
        (is (= #{{3 "person" 7 person0} {3 "person" 7 person1} {3 "person" 7 person2}}
               (into #{} (call task env {3 "person"}))))))))

(deftest check-labels
  (testing "CheckLabels"
    (testing "should return nothing"
      (let [env (IngraphEnvironment. indexer)
            task (->CheckLabels [7 3])]
        (is (empty? (call task env {3 "movie" 7 person0})))))

    (testing "should return vertex"
      (let [env (IngraphEnvironment. indexer)
            task (->CheckLabels [7 3])]
        (is (= #{{3 "person" 7 person0}}
               (into #{} (call task env {3 "person" 7 person0}))))))))

(deftest test-get-edges
  (testing "GetEdges"
    (testing "should return nothing"
      (let [indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->GetEdges [0 1 2])]
        (is (empty? (call task env {})))))

    (testing "should return edges"
      (let [env (IngraphEnvironment. indexer)
            task (->GetEdges [0 1 2])]
        (is (= #{{0 person0 1 p0-knows-p1 2 person1}
                 {0 person1 1 p1-knows-p0 2 person0}
                 {0 person0 1 p0-ch-p2 2 person2}}
               (into #{} (call task env {}))))))))

(deftest test-get-edges-by-type
  (testing "GetEdgesByType"
    (testing "should return nothing"
      (let [indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->GetEdgesByType [4 5 6 7])]
        (is (empty? (call task env {})))))

    (testing "should return edges"
      (let [env (IngraphEnvironment. indexer)
            task (->GetEdgesByType [4 5 6 7])]
        (is (= #{{4 person0 5 p0-knows-p1 6 person1 7 "knows"}
                 {4 person1 5 p1-knows-p0 6 person0 7 "knows"}}
               (into #{} (call task env {7 "knows"}))))))))

(deftest check-type
  (testing "CheckType"
    (testing "should return nothing"
      (let [env (IngraphEnvironment. indexer)
            task (->CheckType [3 1])]
        (is (empty? (call task env {1 "watched" 3 p1-knows-p0})))))

    (testing "should return edge"
      (let [^Indexer indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->CheckType [3 1])]
        (is (= #{{1 "knows" 3 p1-knows-p0}}
               (into #{} (call task env {1 "knows" 3 p1-knows-p0}))))))))

(deftest test-extend-out
  (testing "ExtendOut"
    (testing "should return nothing"
      (let [indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->ExtendOut [2 3 4])]
        (is (empty? (call task env {2 person2})))))

    (testing "should return edges"
      (let [env (IngraphEnvironment. indexer)
            task (->ExtendOut [2 3 4])]
        (is (= #{{2 person0 3 p0-knows-p1 4 person1}
                 {2 person0 3 p0-ch-p2 4 person2}}
               (into #{} (call task env {2 person0}))))))))

(deftest test-extend-in
  (testing "ExtendIn"
    (testing "should return edge"
      (let [indexer (Indexer.)
            env (IngraphEnvironment. indexer)
            task (->ExtendIn [2 3 4])]
        (is (= #{{2 person2 3 p0-ch-p2 4 person0}}
               (into #{} (call task env {2 person2}))))))))

(deftest test-extend-out-by-type
  (testing "ExtendOutByType"
    (testing "should return nothing"
      (let [env (IngraphEnvironment. indexer)
            task (->ExtendOutByType [2 3 4 5])]
        (is (empty? (call task env {2 person2 5 "ate"})))))

    (testing "should return edges"
      (let [env (IngraphEnvironment. indexer)
            task (->ExtendOutByType [2 3 4 5])]
        (is (= #{{2 person0 3 p0-knows-p1 4 person1 5 "knows"}}
               (into #{} (call task env {2 person0 5 "knows"}))))))))

(deftest test-extend-in-by-type
  (testing "ExtendInByType"
    (testing "should return edges"
      (let [env (IngraphEnvironment. indexer)
            task (->ExtendInByType [2 3 4 5])]
        (is (= #{{2 person0 3 p1-knows-p0 4 person1 5 "knows"}}
               (into #{} (call task env {2 person0 5 "knows"}))))))))

(deftest test-join
  (testing "Join"
    (testing "should return edge pointing from source to target only,
    because it takes into account direction"
      (let [env (IngraphEnvironment. indexer)
            task (->Join [2 3 4])]
        (is (= #{{2 person0 3 p0-knows-p1 4 person1}}
               (into #{} (call task env {2 person0 4 person1}))))
        (is (= #{{2 person1 3 p1-knows-p0 4 person0}}
               (into #{} (call task env {2 person1 4 person0}))))))))

(deftest test-join-by-type
  (testing "JoinByType"
    (testing "should return nothing"
      (let [env (IngraphEnvironment. indexer)
            task (->JoinByType [2 3 4 5])]
        (is (empty? (call task env {2 person2 4 person0 5 "knows"})))))
    (testing "should return edge pointing from source to target only,
    because it takes into account direction"
      (let [env (IngraphEnvironment. indexer)
            task (->JoinByType [2 3 4 5])]
        (is (= #{{2 person0 3 p0-knows-p1 4 person1 5 "knows"}}
               (into #{} (call task env {2 person0 4 person1 5 "knows"}))))
        (is (= #{{2 person1 3 p1-knows-p0 4 person0 5 "knows"}}
               (into #{} (call task env {2 person1 4 person0 5 "knows"}))))))))

(deftest test-eval-equals
  (testing "GenEvalEquals"
    (let [c (partial call (->EvalGenBinaryAssertion [9 6 7]) (IngraphEnvironment. indexer))]
      (testing "should return nothing"
        (is (empty? (c {9 person0 6 p0-ch-p2 7 = })))
        (is (empty? (c {9 person0 6 person1 7 = }))))
      (testing "should return somethings"
        (is (some? (c {9 person0 6 person0 7 = })))
        (is (some? (c {9 p0-knows-p1 6 p0-knows-p1 7 = })))))))
