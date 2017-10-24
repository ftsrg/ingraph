(ns sre.execution.executor
  (:require [clojure
             [set :refer :all]
             [zip :as z]]
            [clojure.algo.generic.functor :refer :all]
            [sre.core :refer :all]
            [sre.plan.task :refer [ISearch search]]
            [taoensso.tufte :refer [defnp]]
            [taoensso.tufte :as tufte]
            [clojure.pprint :as pprint])
  (:import [clojure.lang LazySeq PersistentList]))

(set! *warn-on-reflection* true)

(defrecord ZipperNode [variables ctx ^PersistentList left])

(def search-tree-zipper
  (partial z/zipper
           (fn [^ZipperNode node] (some? (:left node)))
           (fn [^ZipperNode node]
             (let [[first & rest] (:left node)
                   heads (search (:type first) (:bindings first)
                                 (:variables node) (:ctx node))
                   children (map #(->ZipperNode %1 (:ctx node) rest) heads)]
               (if-not (empty? children) children)))        ; convert empty list to nil or else strange things happen :(
           (fn [^ZipperNode _ ^LazySeq c] c)))

(def dft (partial iterate #(tufte/p :next (z/next %))))

(def take-until-end (take-while (complement z/end?)))

(def filter-leaf (filter #(or (false? (z/branch? %))
                              (empty? (z/children %)))))

(def filter-complete (filter #(-> % z/node :left nil?)))

(def map-variables (map #(-> % z/node :variables)))

(defn remap-keys [m key-map] (reduce-kv (fn [a k v] (assoc a (key-map k) v)) {} m))

(defn rebind-variables [variables bindings key-map]
  (-> variables (select-keys bindings) (remap-keys key-map)))


(defnp ^:private conj-step-search [this bindings variables ctx subtasks outer-vars inner-vars]
  (let [key-map (zipmap bindings outer-vars)
        variables (tufte/p :bind-vars (rebind-variables variables bindings key-map))
        find-matches (fn [x] (eduction take-until-end
                                       filter-leaf
                                       filter-complete
                                       map-variables
                                       (map #(tufte/p :rebind-vars (rebind-variables % outer-vars (map-invert key-map))))
                                       (dft x)))]
    (find-matches (search-tree-zipper (->ZipperNode variables ctx subtasks)))))


;; Step comprising multiple substeps of pattern matches carried out in a sequence, each of whom must
;; be satisfied to form a complete match. Returns the sequence of complete matches.
(defrecord ConjStep [subtasks outer-vars inner-vars]
  IBind
  (bind [this vals] (->Binding this vals))
  (bind-map [this val-map] (->Binding this (fmap #(val-map %1) (:outer-vars this))))
  ISearch
  (search [this bindings variables ctx] (conj-step-search this bindings variables ctx subtasks outer-vars inner-vars)))


;; Wraps another step and returns the input as a singleton sequence fast on first match, else fails.
(defrecord ExistsStep [inner-search]
  ISearch
  (search [this bindings variables ctx]
    (reduce (constantly [variables]) [] (take 1 (search inner-search bindings variables ctx)))))

;; Wraps another step and fails fast upon first match, else returns the input as a singleton sequence.
(defrecord NotExistsStep [inner-search]
  ISearch
  (search [this bindings variables ctx]
    (reduce (constantly []) [variables] (take 1 (search inner-search bindings variables ctx)))))

;; Step comprising of multiple substeps of pattern matches carried out concurrently and their matches
;; unioned together. We operate on bags, that is same values are NOT deduplicated.
(defrecord UnionStep [subsearches]
  ISearch
  (search [this bindings variables ctx]
    (apply concat (map #(-> % (search bindings variables ctx) lazy-seq) subsearches))))
