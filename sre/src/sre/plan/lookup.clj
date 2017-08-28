(ns sre.plan.lookup
  (:require
    [clojure.algo.generic.functor :refer :all]
    [clojure.set :refer :all]
    [sre.plan.dsl.constraint :refer :all])
  (:import (clojure.lang IPersistentSet)))

(defprotocol IConstraintLookup
  (lookup [this cond] "Gets free/bound constraints. `cond` should be :free or :bound.")
  (lookup-by-type [this cond] "Gets free/bound constraint *bindings* for by type.
  It doesn't return the whole ConstraintBinding only the :bindings part of it!
  (It is trivial to create one as you know the type already")
  (lookup-by-var [this cond] "Gets free/bound constraints by var")
  (bind [this constraint] "If the constraint is free move it to bound, else don't do a thing"))

(defn- create-by-type [bindings]
  (reduce (fn [lkp {type :type bindings :bindings}]
            (if (lkp type)
              (update-in lkp [type] #(conj % bindings))
              (assoc lkp type #{bindings})))
          {}
          bindings))

(defn- create-by-var [bindings]
  (reduce (fn [lkp {vars :bindings :as constraint-binding}]
            (reduce (fn [lkp var]
                      (if (lkp var)
                        (update-in lkp [var] #(conj % constraint-binding))
                        (assoc lkp var #{constraint-binding})))
                    lkp
                    vars))
          {}
          bindings))

(defn- -bind [constr-lkp constraint]
  (if-not ((lookup constr-lkp :free) constraint)
    constr-lkp
    (let [{type :type bindings :bindings} constraint]
      (-> constr-lkp
          (update-in [:free :by-type] #(if (= 1 (count (%1 type)))
                                         ; as an optimization don't leave empty keys hanging in the map
                                         (dissoc %1 type)
                                         (update-in %1 [type] (fn [s] (disj s bindings)))))
          (update-in [:free :by-var] (fn [by-var] (reduce #(if (= 1 (count (%1 %2)))
                                                             ; as an optimization don't leave empty keys hanging in the map
                                                             (dissoc %1 %2)
                                                             (update-in %1 [%2] (fn [s] (disj s constraint))))
                                                          by-var
                                                          bindings)))
          (update-in [:free :constraints] #(disj % constraint))
          (update-in [:bound :by-type] #(merge-with union {type #{bindings}} %))
          (update-in [:bound :by-var] (fn [by-var] (reduce #(merge-with union {%2 #{constraint}} %1)
                                                           by-var
                                                           bindings)))
          (update-in [:bound :constraints] #(conj % constraint))))))

;; I don't want to hassle with implementing ILookup because
;; that would override how get (and probably update) works, and that would make
;; working with this code a total mess I fear.

(defrecord ConstraintLookup [free bound]
  IConstraintLookup
  (lookup [this cond] (-> this cond :constraints))
  (lookup-by-type [this cond] (-> this cond :by-type))
  (lookup-by-var [this cond] (-> this cond :by-var))
  (bind [this constraint] (-bind this constraint)))

(defn constraint-lookup
  "Factory function to create a ConstraintLookup from free and bound constraints.
  Will not expand implications."
  (^ConstraintLookup [free bound]
   (let [free (into #{} free)
         bound (into #{} bound)]
     (map->ConstraintLookup {:free  {:constraints free
                                     :by-type     (create-by-type free)
                                     :by-var      (create-by-var free)}
                             :bound {:constraints bound
                                     :by-type     (create-by-type bound)
                                     :by-var      (create-by-var bound)}})))
  (^ConstraintLookup [free] (constraint-lookup free nil))
  (^ConstraintLookup [] (constraint-lookup nil)))

;; Convienience methods for Java users :)

(defn ConstraintLookupFactory-fromFreeConstrsClosureAndBoundConstrsClosure ^ConstraintLookup [^Iterable free ^Iterable bound]
  (constraint-lookup (seq free) (seq bound)))

(defn ConstraintLookupFactory-fromFreeConstrsClosure ^ConstraintLookup [^Iterable free]
  (constraint-lookup (seq free)))

(defn ConstraintLookupFactory-fromFreeConstrs ^ConstraintLookup [^Iterable free]
  (constraint-lookup (apply union* (seq free))))

(defn ConstraintLookupFactory-fromAllConstrsClosureAndBoundConstrsClosure ^ConstraintLookup [^Iterable all ^Iterable bound]
  (constraint-lookup (difference (into #{} all) (into #{} bound)) (seq bound)))

(defn ConstraintLookupFactory-fromAllConstrsAndBoundConstrs ^ConstraintLookup [^Iterable all ^Iterable bound]
  (constraint-lookup (difference (apply union* (seq all)) (apply union* (seq bound))) (seq bound)))

(gen-class
  :name sre.plan.lookup.ConstraintLookupFactory
  :prefix "ConstraintLookupFactory-"
  :main false
  :methods [^:static [fromFreeConstrsClosureAndBoundConstrsClosure [Iterable Iterable] sre.plan.lookup.ConstraintLookup]
            ^:static [fromFreeConstrsClosure [Iterable] sre.plan.lookup.ConstraintLookup]
            ^:static [fromFreeConstrs [Iterable] sre.plan.lookup.ConstraintLookup]
            ^:static [fromAllConstrsClosureAndBoundConstrsClosure [Iterable Iterable] sre.plan.lookup.ConstraintLookup]
            ^:static [fromAllConstrsAndBoundConstrs [Iterable Iterable] sre.plan.lookup.ConstraintLookup]])
