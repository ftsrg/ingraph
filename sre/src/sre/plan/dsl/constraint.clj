(ns sre.plan.dsl.constraint
  (:require [clojure.set :refer :all]
            [clojure.algo.generic.functor :refer :all]))

(defrecord ConstraintLookup [free bound])

(defrecord ConstraintBinding [type bindings])

(defn constraint-bindings-to-map [bindings]
  (reduce (fn [lkp {type :type bindings :bindings}]
            (if (lkp type)
              (update-in lkp [type] #(conj % bindings))
              (assoc lkp type #{bindings}))) {} bindings))

(defn- bind-implications
  [{bindings :bindings type :type}]
  (let [constraint (deref type)
        lkp (zipmap (:vars constraint) bindings)]
    (fmap (fn [{bindings :bindings type :type}]
            (let [args (fmap #(lkp %1) bindings)]
              (->ConstraintBinding type args)))
          (:implies constraint))))

(defn implies*
  "Returns closure of implications"
  [^ConstraintBinding c-binding]
  (loop [unresolved #{c-binding} result #{}]
    (if (empty? unresolved)
      result
      (let [[first & rest] (into () unresolved)]
        (recur (union (bind-implications first) (into #{} rest)) (conj result first))))))

(defn- iter-implications
  ([] `())
  ([constraint vars & rest] `(~@(apply iter-implications rest) (->ConstraintBinding #'~constraint [~@(map keyword vars)]))))

(defmacro defconstraint
  "Let's you define a constraint like a boss.

  Usage:
    (defconstraint MyConstraint [& vars] :implies* constraint-argument-pairs*)

  Examples:
    (defconstraint Element [element])
    (defconstraint Vertex [vertex] :implies Element[vertex])
    (defconstraint PowerPuffGirls [sugar spice everything-nice]
        :implies
          Element [sugar]
          Element [spice]
          Element [everything-nice]
          Mix [sugar spice everything-nice]"
  [name vars & rest]
  (let [factory-name (str *ns* "." name "ConstraintBinding")
        factory-prefix (str name "ConstraintBinding-")]
    `(do
       (def ~name {:name    #'~name
                   :vars    [~@(map keyword vars)]
                   :arity   ~(count vars)
                   :implies #{~@(let [[implies-kw & implications] rest]
                                  (case implies-kw
                                    nil `()
                                    :implies (apply iter-implications implications)))}})
       (defn ~(symbol (str factory-prefix "create")) [~@vars] (->ConstraintBinding (resolve '~name) [~@vars]))
       (gen-class :name ~factory-name
                  :prefix ~factory-prefix
                  :methods [^:static [~'create [~@(repeat (count vars) int)] Object]])
       ~(if (contains? (ns-interns *ns*) 'constraints)
          `(def ~'constraints (conj ~'constraints ~name)))
       (resolve '~name))))


