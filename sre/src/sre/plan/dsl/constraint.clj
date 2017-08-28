(ns sre.plan.dsl.constraint
  (:require [clojure.set :refer :all]
            [clojure.algo.generic.functor :refer :all])
  (:import (clojure.lang IPersistentSet Var)))

(defn constraint-bindings-to-map [bindings]
  (reduce (fn [lkp {type :type bindings :bindings}]
            (if (lkp type)
              (update-in lkp [type] #(conj % bindings))
              (assoc lkp type #{bindings}))) {} bindings))

(defprotocol IConstraintBinding
  (implies [this] "Returns direct implications")
  (implies* [this] "Returns closure of implications")
  (impliesTransitiveClosure [this] "An alias for implies*. Must do the same thing as implies*"))

(declare -implies -implies*)

(defrecord ConstraintBinding [^Var type bindings]
  IConstraintBinding
  (implies [this] (-implies this))
  (implies* [this] (-implies* this))
  (impliesTransitiveClosure [this] (implies* this)))

(defn- -implies
  ^IPersistentSet [^ConstraintBinding {type :type bindings :bindings}]
  (let [constraint (deref type)
        lkp (zipmap (:vars constraint) bindings)]
    (fmap (fn [{bindings :bindings type :type}]
            (let [args (fmap #(lkp %1) bindings)]
              (->ConstraintBinding type args)))
          (:implies constraint))))

(defn- -implies*
  ^IPersistentSet [^ConstraintBinding c-binding]
  (loop [unresolved #{c-binding} result #{}]
    (if (empty? unresolved)
      result
      (let [[first & rest] (into () unresolved)]
        (recur (union (-implies first) (into #{} rest)) (conj result first))))))

(defn- iter-implications
  ([] `())
  ([constraint vars & rest] `(~@(apply iter-implications rest) (->ConstraintBinding #'~constraint [~@(map keyword vars)]))))

(defmacro defconstraint
  "Let's you define a constraint like a boss.

  Usage:
    (defconstraint MyConstraint [& vars] :implies* constraint-argument-pairs*)

  Examples:
    (defconstraint Element [element])
    (defconstraint Vertex [vertex] :implies Element[vertex])
    (defconstraint PowerPuffGirls [sugar spice everything-nice]
        :implies
          Element [sugar]
          Element [spice]
          Element [everything-nice]
          Mix [sugar spice everything-nice]"
  [name vars & rest]
  (let [factory-name (str *ns* "." name "ConstraintBinding")
        factory-prefix (str name "ConstraintBinding-")]
    `(let [type# (def ~name {:name    #'~name
                             :vars    [~@(map keyword vars)]
                             :arity   ~(count vars)
                             :implies #{~@(let [[implies-kw & implications] rest]
                                            (case implies-kw
                                              nil `()
                                              :implies (apply iter-implications implications)))}})]
       (defn ~(symbol (str factory-prefix "getName")) [] type#)
       (defn ~(symbol (str factory-prefix "create")) [~@vars] (->ConstraintBinding type# [~@vars]))
       (gen-class :name ~factory-name
                  :prefix ~factory-prefix
                  :methods [^:static [~'getName [] clojure.lang.Var]
                            ^:static [~'create [~@(repeat (count vars) Integer)] ConstraintBinding]])
       ~(if (contains? (ns-interns *ns*) 'constraints)
          `(def ~'constraints (conj ~'constraints ~name)))
       type#)))




