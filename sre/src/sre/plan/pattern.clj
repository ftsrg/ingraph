(ns sre.plan.pattern
  (:refer-clojure :exclude [compile])
  (:require [clojure.set :refer :all]
            [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [sre.core :refer :all]
            [sre.plan.constraint :as c]
            [sre.plan.compiler :as compiler]
            [sre.plan.config :as config]
            [sre.plan.estimation :as estimation]
            [sre.plan.task :as task]
            [sre.plan.lookup :as lkp]
            [sre.plan.op :as op]
            [sre.execution.executor :as exec])
  (:import (sre.plan.constraint Variable Constraint ConstraintBinding)
           (clojure.lang IPersistentVector IPersistentSet ILookup)))

(defprotocol IPattern
  "IPattern is rule that has to be satisfied by a search. Every pattern is compiled into a unique Constraint and
  a corresponding Op that satisfies the constraint."
  (compile [this compiler config]))

(extend-type Constraint IPattern (compile [this config compiler-opts] [this nil nil nil]))

(defprotocol IPatternBinding (hoist [this ctx]))

(def zip (partial map vector))

; Pattern defines a conjunction of subpatterns to be satisfied.
; It treats all its vars as if they were existentially quantified.
(defrecord Pattern [name outer-vars inner-vars reqs pattern-bindings]
  IPattern
  (compile [this config compiler-opts]
    (try
      (let [constraint (c/constraint name outer-vars reqs)
            op (op/op name outer-vars reqs #{(bind constraint outer-vars)})
            compiled-stuff (reduce (fn [a x] (map #(if (some? (second %)) (conj (first %) (second %)) (first %)) (zip a x)))
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
            task-bindings (map #(->Binding ((config/tasks config) (:type op)) (:bindings :op)) (:ops plan))
            task (exec/->ConjStep task-bindings outer-vars inner-vars)]
        [constraint op {op (constantly weight)} {op (constantly task)}])
      (catch Throwable t
        (throw (Exception. (str "Cannot compile pattern `" name "'") t))))))

(defn make-pattern
  ([name vars reqs pattern-bindings] (->Pattern name vars
                                                (reduce #(apply conj %1 (:bindings %2)) #{} pattern-bindings)
                                                reqs pattern-bindings))
  ([vars reqs pattern-bindings] (make-pattern (str (gensym ("anon_pattern__"))) vars reqs pattern-bindings)))
