(ns sre.plan.op
  (:refer-clojure :exclude [type])
  (:require [clojure.algo.generic.functor :refer :all]
            [clojure.set :refer :all]
            [clojure.pprint :as pprint]
            [clojure.walk :as walk]
            [sre.plan.constraint :as constraint]
            [sre.core :refer :all])
  (:import (clojure.lang Symbol)
           (java.io Writer)
           (sre.core Binding)))

(defrecord Op [requires satisfies name vars fast-forward])

(defn pprint-op [op]
  (pprint/pprint-logical-block :prefix "<" :suffix ">" (pprint/write-out (-> op :name resolve meta :name))))

(defmethod pprint/simple-dispatch Op [op] (pprint-op op))

(extend-type Op
  IBind
  (bind-map [this var-map]
    (let [replace (partial fmap (fn [v] (fmap (fn [p] (fmap #(var-map %1) p)) v)))]
      (map->Binding {:type      this
                       :bindings  (fmap #(var-map %1) (:vars this))
                       :requires  (replace (:requires this))
                       :satisfies (replace (:satisfies this))})))
  (bind [this args] (bind-map this (zipmap (:vars this) args))))

(defn- expand-implications [constr-defs]
  (apply union (map (fn [[constr vars]]
                      (constraint/implies* (constraint/->ConstraintBinding (deref (resolve constr)) vars)))
                    constr-defs)))

(defn op
  ([name vars reqs sats opts] (map->Op (merge
                                        {:immediate false}
                                        opts
                                        {:name      name
                                         :vars      vars
                                         :requires  (constraint/constraint-bindings-to-map (into () reqs))
                                         :satisfies (constraint/constraint-bindings-to-map (into () sats))})))
  ([vars reqs sats opts] (op (str (gensym "anon_op__")) vars reqs sats opts))
  ([vars reqs sats] (op vars reqs sats {})))


(defn- reqs-sats [args]
  (let [[reqs [-> & sats]] (split-with (fn [x] (not= x '->)) args)]
    [reqs sats]))

(defmacro defop
  "Let's you define an operation. Generates a Java factory for creating a Binding for this op,
  getting the type etc., and adds the op to the configuration (if there is one).

  Usage:
    (defop MyOp [& vars] constraint-argument-pairs* -> constraint-argument-pairs*)

  Examples:
  (defop ExtendOut [source edge target]
      Vertex [source] -> DirectedEdge [source edge target])

  (defop CreatePowerPuffGirls [sugar spice everything]
                           Sugar [sugar] Spice [spice] Nice [everything] -> PowerPuffGirls [sugar spice everything])
  "
  [name vars & reqs+sats+opts]
  (let [var-map (zipmap vars (map keyword vars))
        factory-name (str *ns* "." name "OperationBinding")
        factory-prefix (str name "OperationBinding-")
        [reqs+sats [_ opts]] (split-with #(not= % :opts) reqs+sats+opts)
        [reqs sats] (reqs-sats (map #(walk/postwalk-replace var-map %) reqs+sats))
        req-set (expand-implications (partition 2 reqs))
        sat-set (difference (expand-implications (partition 2 sats)) req-set)]
    `(let [type# (def ~name (op '~(symbol (str *ns*) (str name))
                                ~(walk/postwalk-replace var-map vars)
                                ~req-set
                                ~sat-set
                                ~opts))]
                                        ; add to configuration if exists
       ~(if (contains? (ns-interns *ns*) '-ops)
          `(def ~'-ops (conj ~'-ops ~name)))
                                        ; create factory
       (defn ~(symbol (str factory-prefix "getType")) ^Op [] ~name)
       (defn ~(symbol (str factory-prefix "create")) ^Binding [~@vars] (bind ~name ~vars))
       (gen-class
        :name ~factory-name
        :prefix ~factory-prefix
        :methods [^:static [~'getType [] sre.plan.op.Op]
                  ^:static [~'create [~@(repeat (count vars) Object)] sre.core.Binding]])
       type#)))
