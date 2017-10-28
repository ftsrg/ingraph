(ns sre.plan.pattern
  (:refer-clojure :exclude [compile not name])
  (:require [clojure.algo.generic.functor :refer [fmap]]
            [clojure.set :refer :all]
            [sre.core :refer :all]
            [sre.execution.executor :as exec]
            [sre.plan
             [compiler :as compiler]
             [config :as config]
             [constraint :as c]
             [estimation :as estimation]
             [lookup :as lkp]
             [op :as op]
             [task :as task]]
            [clojure.pprint :as pprint])
  (:import sre.plan.constraint.Constraint))

(defprotocol IPattern
  "IPattern is rule that has to be satisfied by a search. Every pattern is compiled into a unique Constraint and
  a corresponding Op that satisfies the constraint."
  (name [this])
  (outer-vars [this])
  (constraint [this])
  (op [this])
  (compile [this compiler config]))

(extend-type Constraint IPattern (compile [this config compiler-opts] [this nil nil nil]))

(defprotocol IPatternBinding (hoist [this ctx]))

(def zip (partial map vector))

(defn- extract-vars [cbs] (reduce #(apply conj (concat [%1] (:bindings %2))) #{} cbs))

;; Pattern defines a conjunction of subpatterns to be satisfied.
;; It treats all its vars as if they were existentially quantified.
(defrecord Pattern [name outer-vars inner-vars reqs pattern-bindings constraint op]
  IBind
  (bind [this vals]
    (bind-map this (zipmap (:outer-vars this) vals)))
  (bind-map [this val-map]
    (map->Binding {:type     this
                   :bindings (fmap #(val-map %1) (:outer-vars this))}))
  IPattern
  (name [this] (:name this))
  (outer-vars [this] (:outer-vars this))
  (op [this] (:op this))
  (constraint [this] (:constraint this))
  (compile [this config compiler-opts]
    (try
      (let [compiled-stuff (reduce (fn [a x] (map #(if (some? (second %)) (conj (first %) (second %)) (first %)) (zip a x)))
                                   [#{} [] [] {} {}]
                                   (for [{t :type b :bindings} pattern-bindings
                                         :let [conf-update (compile t config compiler-opts)
                                               cb (bind (first conf-update) b)]]
                                     (cons cb conf-update)))
            ;; create constraint lookup table for the search planner
            [cbs & config-updates] compiled-stuff

            ;; explode implications
            reqs (apply c/union* reqs)
            cbs (apply c/union* cbs)
            constr-lkp (lkp/constraint-lookup (difference cbs reqs) reqs)
            ;; update config by applying all configuration updates in order to resolve any
            ;; nested patterns
            config (apply config/config (concat [name] config-updates [config]))
            ;; run the search planner
            plan @(compiler/run constr-lkp config compiler-opts)
            weight (-> plan :cost-calculator estimation/to-weight)
            task-bindings (map #(->Binding ((config/tasks config) (:type %)) (:bindings %)) (:ops plan))
            task (exec/->ConjStep task-bindings outer-vars inner-vars)]
        [constraint op {op (constantly weight)} {op (constantly task)}])
      (catch Throwable t
        (throw (Exception. (str "Cannot compile pattern `" name "'") t))))))

(defn pattern
  ([name vars reqs pattern-bindings] (let [constraint (c/constraint name vars reqs)
                                           op (op/op name vars reqs #{(bind constraint vars)} {})]
                                       (map->Pattern {:name name
                                                      :outer-vars vars
                                                      :inner-vars (extract-vars pattern-bindings)
                                                      :reqs reqs
                                                      :pattern-bindings pattern-bindings
                                                      :constraint constraint
                                                      :op op})))
  ([vars reqs pattern-bindings] (pattern (str (gensym "anon_pattern__")) vars reqs pattern-bindings)))

(defrecord Not [name pattern outer-vars inner-vars constraint op]
  IBind
  (bind [this vals]
    (bind-map this (zipmap (:outer-vars this) vals)))
  (bind-map [this val-map]
    (map->Binding {:type     this
                   :bindings (fmap #(val-map %1) (:outer-vars this))}))
  IPattern
  (name [this] (:name this))
  (outer-vars [this] (:outer-vars this))
  (op [this] (:op this))
  (constraint [this] (:constraint this))
  (compile [this config compiler-opts]
    (try
      (let [[inner-constr inner-op inner-weight inner-task] (compile pattern config compiler-opts)
            ;; TODO find good weight
            weight 0.5
            task (exec/->NotExistsStep ((inner-task inner-op)))]
        [constraint op {op (constantly weight)} {op (constantly task)}]))))

(defn not [pattern]
  (let [name (str (gensym "not__") "__" (:name pattern))
        inner-vars (into #{} (:outer-vars pattern))
        req-vars (extract-vars (:reqs pattern))
        outer-vars (-> pattern :outer-vars (as-> v (filter req-vars v) (into [] v)))
        constraint (c/constraint name outer-vars (:reqs pattern))
        op (op/op name outer-vars (:reqs pattern) #{(bind constraint outer-vars)} {})]
    (map->Not{:name name
              :pattern pattern
              :outer-vars outer-vars
              :inner-vars inner-vars
              :constraint constraint
              :op op})))

(defn get-tasks [pattern] (((nth pattern 3) (nth pattern 1))))

(defn run [query bindings ctx]
  (task/search (get-tasks query) bindings {} ctx))
