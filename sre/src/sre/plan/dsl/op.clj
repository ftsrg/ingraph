(ns sre.plan.dsl.op
  (:require [clojure.algo.generic.functor :refer :all]
            [clojure.set :refer :all]
            [sre.plan.dsl.constraint :as constraint]
            [clojure.pprint :as pprint])
  (:import (clojure.lang Symbol)
           (java.io Writer)))

(defn- expand-implications [constr-defs]
  (apply union (map (fn [[constr vars]]
                      (constraint/implies*
                        (constraint/->ConstraintBinding (deref (resolve constr)) vars)))
                    constr-defs)))

(defn- create-map [pairs]
  (as-> pairs x
        (fmap #(update-in % [:bindings] (fn [bs] (fmap (fn [b] (keyword b)) bs))) x)
        (constraint/constraint-bindings-to-map x)))

(defn- reqs-sats [args]
  (let [[reqs [-> & sats]] (split-with (fn [x] (not= x '->)) args)]
    [reqs sats]))

(defn- compile-args [args]
  (let [[reqs sats] (reqs-sats args)
        expanded-reqs (expand-implications (partition 2 reqs))
        expanded-sats (expand-implications (partition 2 sats))]
    [(create-map (into () expanded-reqs))
     (create-map (into () (difference expanded-sats expanded-reqs)))]))

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

(defmacro op
  "Creates an operation.

  Usage:
    (op MyOp [& vars] constraint-argument-pairs* -> constraint-argument-pairs*)

  Examples:
    (op ExtendOut [source edge target]
      Vertex [source] -> DirectedEdge [source edge target])

    (op CreatePowerPuffGirls [sugar spice everything
      Sugar [sugar] Spice [spice] Nice [everything] -> PowerPuffGirls [sugar spice everything])
  "
  [type vars & rest]
  `(map->Op (merge ~(let [[req-map sat-map] (compile-args rest)]
                      {:requires  req-map
                       :satisfies sat-map})
                   {:name '~(symbol (str *ns* "/" type))
                    :vars [~@(map keyword vars)]})))

(defmacro defop
  "Let's you define an operation. Additionally to being a shorthand for
  (def name (op name ...)) generates a Java factory for creating an OpBinding for this op,
  getting the type etc., and adds the op to the configuration (if there is one).

  Usage:
    (defop MyOp [& vars] constraint-argument-pairs* -> constraint-argument-pairs*)

  "
  [name vars & rest]
  (let [factory-name (str *ns* "." name "OperationBinding")
        factory-prefix (str name "OperationBinding-")]
    `(let [type# (def ~name (op ~name ~vars ~@rest))]
       ; add to configuration if exists
       ~(if (contains? (ns-interns *ns*) 'ops)
          `(def ~'ops (conj ~'ops ~name)))
       ; create factory
       (defn ~(symbol (str factory-prefix "getType")) ^Op [] ~name)
       (defn ~(symbol (str factory-prefix "create")) ^OpBinding [~@vars] (bind ~name ~@vars))
       (gen-class
         :name ~factory-name
         :prefix ~factory-prefix
         :methods [^:static [~'getType [] sre.plan.dsl.op.Op]
                   ^:static [~'create [~@(repeat (count vars) Object)] sre.plan.dsl.op.OpBinding]])
       type#)))
