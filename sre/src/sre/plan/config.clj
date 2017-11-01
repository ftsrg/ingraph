(ns sre.plan.config
  "Search planning configuration determines the available
  platform-dependent constraints, operations, tasks and
  cost estimators.

  Note: To avoid warnings about replacing clojure.core/name
  its best to require this file qualified or under an alias,
  or if you are defining a configuration in a dedicated file
  just add (:refer-clojure :exclude [name]) to the ns declaration."
  (:refer-clojure :exclude [name])
  (:require [clojure.set :refer :all]
            [clojure.pprint :refer :all]
            [sre.core :refer :all]
            [sre.plan.op :refer :all])
  (:import (clojure.lang IPersistentSet MultiFn)
           (sre.plan.op Op)))

(defprotocol IConfig
  "Configuration used to store constraints, operations, etc."
  (name [this] "Returns the name of this config ")
  (constraints [this] "Constraints provided by this config")
  (operations [this] "Operations provided by this config")
  (costs [this] "Operation cost estimator provided by this config")
  (tasks [this] "Tasks provided by this config"))

(def ^:private dispatch-op (fn ^Op [op & rest] op))

(def ^:private h (make-hierarchy))

(defrecord Config [^String name ^IPersistentSet constraints ^IPersistentSet ops ^MultiFn costs ^MultiFn tasks]
  IConfig
  (name [this] name)
  (constraints [this] constraints)
  (operations [this] ops)
  (costs [this] costs)
  (tasks [this] tasks))

(defn config
  "Creates a config. Optionally you can specify an underlying config upon which
  this one will be layered. Constraints and operations will be united, name will be overshadowed
  cost and task methods will be coalesced in a way that first the upper config will be checked then
  if not found dispatch will fall through to the underlying multimethod."
  ([name constrs ops cost-methods task-methods underlying]
   (let [cost-dispatch (MultiFn. (str name "-cost") dispatch-op :default #'h)
         tasks-dispatch (MultiFn. (str name "-tasks") dispatch-op :default #'h)
         constrs (union constrs (if underlying (constraints underlying)))
         ops (union ops (if underlying (operations underlying)))
         cost-methods (merge (if underlying (-> underlying costs methods)) cost-methods)
         task-methods (merge (if underlying (-> underlying tasks methods)) task-methods)]
     ;; sidefx below!
     (doseq [[dispatch-val mfn] cost-methods]
       (.addMethod cost-dispatch dispatch-val mfn))
     (doseq [[dispatch-val mfn] task-methods]
       (.addMethod tasks-dispatch dispatch-val mfn))
     (->Config name constraints ops cost-dispatch tasks-dispatch)))
  ([name constrs ops cost-methods task-methods] (config name
                                                          (into #{} constrs)
                                                          (into #{} ops)
                                                          cost-methods task-methods nil)))


(defmacro defconfig
  "Creates an engine configuration variable in the current namespace, used
  to store constraints, operations, etc., serving as a container for the DSL.
  Similar to an EMF container in concept LOL.

  Interns a singleton object implementing the IConfig protocol. Also creates
  a wrapper class for accessing from Java.

  Make sure you only have one defconfig in a namespace, and that it comes
  before all other DSL macro calls!

  Syntax:

  (defconfig name)

  Usage:
  ```
  (ns myconfig (:require
                 [sre.plan.config
                 [sre.plan.constraint :refer [defconstraint]]
                 [sre.plan.op :refer [defop defweight]])))

  (defconfig MyConfig)

  (defconstraint Constraint01)
  ...

  (defop Op01)
  ```

  Now you can get all ops from myconfig/ops etc."
  [name]
  (let [factory-name (str *ns* "." name "Config")
        factory-prefix (str name "Config-")]
    `(do
       (def ^:private ~'-name (str '~name))
       (def ^:private ~'-ops #{})
       (def ^:private ~'-constraints #{})
       (defmulti ^:private ~'-costs ~dispatch-op)
       (defmulti ^:private ~'-tasks ~dispatch-op)

       (def ~name (reify IConfig
                    (name [~'this] ~'-name)
                    (constraints [~'this] ~'-constraints)
                    (operations [~'this] ~'-ops)
                    (costs [~'this] ~'-costs)
                    (tasks [~'this] ~'-tasks)))

       (defn ~(symbol (str factory-prefix "getInstance")) [] ~name)
       (gen-class :name ~factory-name
                  :prefix ~factory-prefix
                  :methods [^:static [~'getInstance [] sre.plan.config.IConfig]]))))
