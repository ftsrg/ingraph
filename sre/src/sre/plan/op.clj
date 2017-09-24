(ns sre.plan.op
  (:require [clojure.algo.generic.functor :refer :all]
            [clojure.set :refer :all]
            [sre.plan.constraint :as constraint]
            [clojure.pprint :as pprint]
            [clojure.walk :as walk])
  (:import (clojure.lang Symbol)
           (java.io Writer)))

(defrecord Op [requires satisfies name vars])

(defn pprint-op [op]
  (pprint/pprint-logical-block :prefix "<" :suffix ">" (pprint/write-out (:name op))))

(defmethod pprint/simple-dispatch Op [op] (pprint-op op))

(defprotocol IOpBinding
  (optype [this] "Returns the type of this IOpBinding")
  (requires [this] "Returns required constraints")
  (satisfies [this] "Returns satisfied constraints. This is a superset of the required constraints."))

(defrecord OpBinding [^Op type bindings requires satisfies]
  IOpBinding
  (optype [this] type)
  (requires [this] requires)
  (satisfies [this] satisfies))

(defn pprint-op-binding [op-b]
  (pprint/pprint-logical-block
    :prefix "<" :suffix ">"
    (pprint/write-out (:type op-b))
    (.write ^Writer *out* "::")
    (pprint/write-out (:bindings op-b))))

(defmethod pprint/simple-dispatch OpBinding [op-b] (pprint-op-binding op-b))

(defn bind-map
  "Binds operation parameters to the given name - value map"
  [op lkp]
  (let [replace (partial fmap (fn [values]
                                (fmap (fn [params] (fmap #(lkp %1) params))
                                      values)))]
    (map->OpBinding {:type      op
                     :bindings  (fmap #(lkp %1) (:vars op))
                     :requires  (replace (:requires op))
                     :satisfies (replace (:satisfies op))})))

(defn bind
  "Binds operation parameters to the given arguments"
  [op & args]
  (bind-map op (zipmap (:vars op) args)))

(defn- expand-implications [constr-defs]
  (apply union (map (fn [[constr vars]]
                      (constraint/implies* (constraint/->ConstraintBinding (deref (resolve constr)) vars)))
                    constr-defs)))

(defn op
  ([name vars reqs sats] (map->Op {:name      name
                                   :vars      vars
                                   :requires  (constraint/constraint-bindings-to-map (into () reqs))
                                   :satisfies (constraint/constraint-bindings-to-map (into () sats))}))
  ([vars reqs sats] (op (gensym "anon_op__") vars reqs sats)))

(defn- reqs-sats [args]
  (let [[reqs [-> & sats]] (split-with (fn [x] (not= x '->)) args)]
    [reqs sats]))

(defmacro defop
  "Let's you define an operation. Generates a Java factory for creating an OpBinding for this op,
  getting the type etc., and adds the op to the configuration (if there is one).

  Usage:
    (defop MyOp [& vars] constraint-argument-pairs* -> constraint-argument-pairs*)

  Examples:
  (defop ExtendOut [source edge target]
      Vertex [source] -> DirectedEdge [source edge target])

  (defop CreatePowerPuffGirls [sugar spice everything]
                           Sugar [sugar] Spice [spice] Nice [everything] -> PowerPuffGirls [sugar spice everything])
  "
  [name vars & rest]
  (let [var-map (zipmap vars (map keyword vars))
        factory-name (str *ns* "." name "OperationBinding")
        factory-prefix (str name "OperationBinding-")
        [reqs sats] (reqs-sats (map #(walk/postwalk-replace var-map %) rest))
        req-set (expand-implications (partition 2 reqs))
        sat-set (difference (expand-implications (partition 2 sats)) req-set)]
    `(let [type# (def ~name (op '~(symbol (str *ns*) (str name))
                                ~(walk/postwalk-replace var-map vars)
                                ~req-set
                                ~sat-set))]
       ; add to configuration if exists
       ~(if (contains? (ns-interns *ns*) '-ops)
          `(def ~'-ops (conj ~'-ops ~name)))
       ; create factory
       (defn ~(symbol (str factory-prefix "getType")) ^Op [] ~name)
       (defn ~(symbol (str factory-prefix "create")) ^OpBinding [~@vars] (bind ~name ~@vars))
       (gen-class
         :name ~factory-name
         :prefix ~factory-prefix
         :methods [^:static [~'getType [] sre.plan.op.Op]
                   ^:static [~'create [~@(repeat (count vars) Object)] sre.plan.op.OpBinding]])
       type#)))
