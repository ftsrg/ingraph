(ns sre.plan.dsl.op
  (:require [clojure.algo.generic.functor :refer :all]
            [clojure.set :refer :all]
            [sre.plan.dsl.constraint :as constraint]))

(defn- expand-implications [constr-defs]
  (apply union (map (fn [[constr-sym vars]]
                      (constraint/implies*
                        (constraint/->ConstraintBinding (resolve constr-sym) vars)))
                    constr-defs)))

(defn- create-map [pairs]
  (as-> pairs x
        (fmap #(update-in % [:bindings] (fn [bs] (fmap (fn [b] (keyword b)) bs))) x)
        (constraint/constraint-bindings-to-map x)))

(defn- reqs-sats [args]
  (let [[[req-kw & reqs] [sat-kw & sats]] (split-with (fn [x] (not= `~x :satisfies)) args)]
    [reqs sats]))

(defn- compile-args [args]
  (let [[reqs sats] (reqs-sats args)
        expanded-reqs (expand-implications (partition 2 reqs))
        expanded-sats (expand-implications (partition 2 sats))]
    [(create-map (into () expanded-reqs))
     (create-map (into () (difference expanded-sats expanded-reqs)))]))

(defmacro defop
  "Let's you define an operation.

  Usage:
    (defop MyOp [& vars] :requires constraint-argument-pairs* :satisfies constraint-argument-pairs*)

  Examples:
    (defop ExtendOut [source edge target]
      :requires (Vertex [source])
      :satisfies DirectedEdge [source edge target])

    (defop CreatePowerPuffGirls [sugar spice everything
      :requires Sugar [sugar] Spice [spice] Nice [everything]
      :satisfies PowerPuffGirls [sugar spice everything])
  "
  [name vars & rest]
  (let [factory-name (str *ns* "." name "OperationBinding")
        factory-prefix (str name "OperationBinding-")]
    `(let [type# (def ~name
                   (merge ~(let [[req-map sat-map] (compile-args rest)]
                             {:requires  req-map
                              :satisfies sat-map})
                          {:name #'~name
                           :vars [~@(map keyword vars)]}))]
       ~(if (contains? (ns-interns *ns*) 'ops)
          `(def ~'ops (conj ~'ops ~name)))
       (defn ~(symbol (str factory-prefix "getName")) [] type#)
       (gen-class
         :name ~factory-name
         :prefix ~factory-prefix
         :methods [^:static [~'getName [] clojure.lang.Var]])
       type#)))

(defn bind
  "Binds operation parameters to the given arguments"
  [op & args]
  (let [lkp (zipmap (:vars op) args)
        replacer (partial fmap (fn [values]
                                 (fmap (fn [params] (fmap #(lkp %1) params))
                                       values)))]
    (-> op
        (update-in [:vars] (partial fmap #(lkp %1)))
        (update-in [:requires] replacer)
        (update-in [:satisfies] replacer))))

(defn bind-map
  "Binds operation parameters to the given name - value map"
  [op map]
  (let [lkp map
        replacer (partial fmap (fn [values]
                                 (fmap (fn [params] (fmap #(lkp %1) params))
                                       values)))]
    (-> op
        (update-in [:vars] (partial fmap #(lkp %1)))
        (update-in [:requires] replacer)
        (update-in [:satisfies] replacer))))

