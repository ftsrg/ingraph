(ns sre.execution.executor
  (:require [clojure.zip :as z]
            [sre.plan.task :refer :all])
  (:import (clojure.lang LazySeq PersistentHashMap PersistentList)
           (java.util Vector)
           (sre.plan.task SearchTreeState)))

(defrecord SearchTreeZipperNode [^SearchTreeState state ^PersistentList left])

(def search-tree-zipper
  (partial z/zipper
           (fn [^SearchTreeZipperNode node] (some? (:left node)))
           (fn [^SearchTreeZipperNode node]
             (let [[first & rest] (:left node)
                   ^SearchTreeState state (:state node)
                   states (search first state)
                   children (map #(->SearchTreeZipperNode %1 rest) states)]
               (if-not (empty? children) children)))        ; convert empty list to nil or else strange things happen :(
           (fn [^SearchTreeZipperNode _ ^LazySeq c] c)))

(def dft (partial iterate z/next))

(def take-until-end (take-while (complement z/end?)))

(def filter-leaf (filter #(or (false? (z/branch? %)) (empty? (z/children %)))))

(def filter-complete (filter #(-> % z/node :left nil?)))

(def map-state (map #(-> % z/node :state)))

;; Step comprising multiple substeps of pattern matches carried out in a sequence, each of whom must
;; be satisfied to form a complete match. Returns the sequence of complete matches."
(defrecord ConjStep [subsearches]
  ISearch
  (search [this state]
    (let [find-matches #(eduction take-until-end filter-leaf filter-complete map-state (dft %))]
      (find-matches (search-tree-zipper (->SearchTreeZipperNode state subsearches))))))

;; Wraps another step and returns the input as a singleton sequence fast on first match, else fails."
(defrecord ExistsStep [inner-search]
  ISearch
  (search [this state] (reduce (constantly [state]) [] (take 1 (search inner-search state)))))

;; Wraps another step and fails fast upon first match, else returns the input as a singleton sequence."
(defrecord NotExistsStep [inner-search]
  ISearch
  (search [this state] (reduce (constantly []) [state] (take 1 (search inner-search state)))))

;; Step comprising of multiple substeps of pattern matches carried out concurrently and their matches
;; unioned together. We operate on bags, that is same values are NOT deduplicated.
(defrecord UnionStep [subsearches]
  ISearch
  (search [this state] (apply concat (map #(-> % (search state) lazy-seq) subsearches))))
