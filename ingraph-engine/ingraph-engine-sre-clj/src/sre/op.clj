(ns sre.op
  (:require [clojure.algo.generic.functor :refer :all]))

(defmacro defop [name vars & rest]
  "Let's you define a bloody operation.

  Usage:
    (defop MyOp [& vars] :requires consraint-argument-pairs* :satisfies constraint-argument-pairs*)

  Examples:
    (defop ExtendOut [source edge target]
      :requires (Vertex [source])
      :satisfies DirectedEdge [source edge target])

    (defop CreatePowerPuffGirls [sugar spice everything
      :requires Sugar [sugar] Spice [spice] Nice [everything]
      :satisfies PowerPuffGirls [sugar spice everything])
  "
  (defn- create-map [pairs]
    (reduce (fn [a [k v]] (if-not (nil? (a `#'~k))
                            (update-in a [`#'~k] (fn [x] (into [] (cons `[~@(map keyword v)] x))))
                            (assoc a `#'~k `[[~@(map keyword v)]]))) {} pairs))

  (defn- extract-conditions [args]
    (let [[[req-kw & reqs] [sat-kw & sats]] (split-with (fn [x] (not= `~x :satisfies)) args)]
      [(create-map (partition 2 reqs)) (create-map (partition 2 sats))]))

  `(def ~name
     (merge ~(let [[req sat] (extract-conditions rest)]
               {:requires req
                :satisfies sat})
            {:name #'~name
             :vars [~@(map keyword vars)] })))

(defn bind [op & args]
  "Binds operation parameters to the given arguments"
  (let [lkp (zipmap (:vars op) args)
        replacer (partial fmap (fn [values]
                                 (fmap (fn [params] (fmap #(lkp %1) params))
                                       values)))]
    (-> op
        (update-in [:vars] (partial fmap #(lkp %1)))
        (update-in [:requires] replacer)
        (update-in [:satisfies] replacer))))