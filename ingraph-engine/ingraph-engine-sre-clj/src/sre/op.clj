(ns sre.op
  (:require [clojure.algo.generic.functor :refer :all]
            [clojure.set :refer :all]
            [sre.constraint]))

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
  (defn- expand-implications [constr-defs]
    (apply union (->> constr-defs
                      (map (fn [[f s]]
                             (apply sre.constraint/bind (deref (resolve f)) s)))
                      (map (comp
                             (partial fmap #(vector (:name %1) (:vars %1)))
                             sre.constraint/implies*)))))

  (defn- create-map [pairs]
    (reduce (fn [[lkp vars] [k v]] [(if-not (nil? (lkp k))
                            (update-in lkp [k] (fn [x] (into [] (cons `[~@(map keyword v)] x))))
                            (assoc lkp k `[[~@(map keyword v)]])) (union vars `#{~@(map keyword v)})]) [{} #{}] pairs))

  (defn- extract-conditions [args]
    (let [[[req-kw & reqs] [sat-kw & sats]] (split-with (fn [x] (not= `~x :satisfies)) args)
          expanded-reqs (expand-implications (partition 2 reqs))
          expanded-sats (expand-implications (partition 2 sats))]
      [(create-map (into () expanded-reqs)) (create-map (into () (difference expanded-sats expanded-reqs)))]))

  `(def ~name
     (merge ~(let [[[req-map req-vars] [sat-map sat-vars]] (extract-conditions rest)]
               {:requires req-map
                :satisfies sat-map
                :cardinality (count (difference sat-vars req-vars))})
            {:name #'~name
             :vars [~@(map keyword vars)] })))

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
