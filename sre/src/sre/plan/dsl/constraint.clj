(ns sre.plan.dsl.constraint
  (:require [clojure.set :refer :all]
            [clojure.algo.generic.functor :refer :all])
  (:import (clojure.lang IPersistentSet Var)))

(defrecord Variable [name])

(defn constraint-bindings-to-map [bindings]
  (reduce (fn [lkp {type :type bindings :bindings}]
            (if (lkp type)
              (update-in lkp [type] #(conj % bindings))
              (assoc lkp type #{bindings}))) {} bindings))

(defprotocol IConstraintBinding
  (implies [this] "Returns direct implications")
  (implies* [this] "Returns closure of implications")
  (impliesTransitively [this] "An alias for implies*"))

(declare -implies -implies*)

(defn union* [& constrs] (reduce #(union %1 (implies* %2)) #{} constrs))

(defrecord ConstraintBinding [^Var type bindings]
  IConstraintBinding
  (implies [this] (-implies this))
  (implies* [this] (-implies* this))
  (impliesTransitively [this] (implies* this)))

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
    (defconstraint Vertex [vertex] < Element [vertex])
    (defconstraint PowerPuffGirls [sugar spice everything-nice]
        < Element [sugar]
          Element [spice]
          Element [everything-nice]
          Mix [sugar spice everything-nice]"
  [name vars & rest]
  (let [factory-name (str *ns* "." name "ConstraintBinding")
        factory-prefix (str name "ConstraintBinding-")]
    `(let [type# (def ~name {:type    #'~name
                             :vars    [~@(map keyword vars)]
                             :arity   ~(count vars)
                             :implies #{~@(let [[implies-kw & implications] rest]
                                            (case implies-kw
                                              nil `()
                                              '< (apply iter-implications implications)))}})]
       (defn ~(symbol (str factory-prefix "getType")) [] type#)
       (defn ~(symbol (str factory-prefix "create")) [~@vars] (->ConstraintBinding type# [~@vars]))
       (gen-class :name ~factory-name
                  :prefix ~factory-prefix
                  :methods [^:static [~'getType [] clojure.lang.Var]
                            ^:static [~'create [~@(repeat (count vars) Object)] ConstraintBinding]])
       ~(if (contains? (ns-interns *ns*) 'constraints)
          `(def ~'constraints (conj ~'constraints ~name)))
       type#)))




