(ns sre.plan.dsl.constraint
  (:require [clojure.set :refer :all]
            [clojure.algo.generic.functor :refer :all]
            [clojure.pprint :as pprint]
            [clojure.walk :as walk])
  (:import (clojure.lang IPersistentSet Symbol)
           (java.io Writer)))

(defrecord Variable [name])

(defrecord Constraint [name vars arity implies])

(defn pprint-constr [type]
  (pprint/pprint-logical-block :prefix "<" :suffix ">" (pprint/write-out (:name type))))

(defmethod pprint/simple-dispatch Constraint [type] (pprint-constr type))

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

(defrecord ConstraintBinding [^Constraint type bindings]
  IConstraintBinding
  (implies [this] (-implies this))
  (implies* [this] (-implies* this))
  (impliesTransitively [this] (implies* this)))

(defn pprint-constraint-binding [c-b]
  (pprint/pprint-logical-block
    :prefix "<" :suffix ">"
    (pprint/write-out (:type c-b))
    (.write ^Writer *out* "::")
    (pprint/write-out (:bindings c-b))))

(defmethod pprint/simple-dispatch ConstraintBinding [c-b] (pprint-constraint-binding c-b))

(defn bind-map
  "Binds constraint parameters to the given name - value map"
  [constr lkp]
  (map->ConstraintBinding {:type constr :bindings (fmap #(lkp %1) (:vars constr))}))

(defn bind
  "Binds constraint parameters to the given arguments"
  [op & args]
  (bind-map op (zipmap (:vars op) args)))

(defn- -implies
  ^IPersistentSet [^ConstraintBinding {type :type bindings :bindings}]
  (let [constr type
        lkp (zipmap (:vars constr) bindings)]
    (fmap (fn [{bindings :bindings type :type}]
            (let [args (fmap #(lkp %1) bindings)]
              (->ConstraintBinding type args)))
          (:implies constr))))

(defn- -implies*
  ^IPersistentSet [^ConstraintBinding c-binding]
  (loop [unresolved #{c-binding} result #{}]
    (if (empty? unresolved)
      result
      (let [[first & rest] (into () unresolved)]
        (recur (union (-implies first) (into #{} rest)) (conj result first))))))

(defn union* [& constrs] (reduce #(union %1 (implies* %2)) #{} constrs))

(defn constraint
  ([name vars implications] (map->Constraint {:name name
                                              :vars vars
                                              :arity (count vars)
                                              :implies (->> implications
                                                            (partition 2)
                                                            (map (fn [[type vars]] (->ConstraintBinding type vars)))
                                                            (into #{}))}))
  ([vars implications] (constraint (gensym "anon_constraint__") vars implications)))

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
  (let [[< & implications] rest
        var-map (zipmap vars (map keyword vars))
        factory-name (str *ns* "." name "ConstraintBinding")
        factory-prefix (str name "ConstraintBinding-")]
    `(let [type# (def ~name (constraint '~(symbol (str *ns*) (str name))
                                        ~(walk/postwalk-replace var-map vars)
                                        [~@(walk/postwalk-replace var-map implications)]))]
       (defn ~(symbol (str factory-prefix "getType")) ^Constraint [] ~name)
       (defn ~(symbol (str factory-prefix "create")) ^ConstraintBinding [~@vars] (->ConstraintBinding ~name [~@vars]))
       (gen-class :name ~factory-name
                  :prefix ~factory-prefix
                  :methods [^:static [~'getType [] sre.plan.dsl.constraint.Constraint]
                            ^:static [~'create [~@(repeat (count vars) Object)] sre.plan.dsl.constraint.ConstraintBinding]])
       ~(if (contains? (ns-interns *ns*) 'constraints)
          `(def ~'constraints (conj ~'constraints ~name)))
       type#)))




