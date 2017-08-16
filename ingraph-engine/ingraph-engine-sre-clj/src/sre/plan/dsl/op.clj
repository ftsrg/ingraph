(ns sre.plan.dsl.op
  (:require [clojure.algo.generic.functor :refer :all]
            [clojure.set :refer :all]
            [sre.plan.dsl.constraint]))

(defn- expand-implications [constr-defs]
  (apply union (->> constr-defs
                    (map (fn [[f s]]
                           (apply sre.plan.dsl.constraint/bind (deref (resolve f)) s)))
                    (map (comp
                           (partial fmap #(vector (:name %1) (:vars %1)))
                           sre.plan.dsl.constraint/implies*)))))

(defn- create-map [pairs]
  (reduce (fn [lkp [k v]] (if-not (nil? (lkp k))
                            (update-in lkp [k] (fn [x] (into [] (cons `[~@(map keyword v)] x))))
                            (assoc lkp k `[[~@(map keyword v)]]))) {} pairs))

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
  "Let's you define a bloody operation.

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

  `(do
     (def ~name
       (merge ~(let [[req-map sat-map] (compile-args rest)]
                 {:requires  req-map
                  :satisfies sat-map})
              {:name #'~name
               :vars [~@(map keyword vars)]}))
     ~(if (contains? (ns-interns *ns*) 'ops)
        `(def ~'ops (conj ~'ops ~name)))))

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

(defmulti weight (fn [x & rest] (:name x)))

(defmacro defweight [op params body]
  "Let's you define a weight for an operation"
  `(defmethod weight #'~op ~params ~body))

