(ns sre.plan.config
  "Search planning configuration determines the available
  platform-dependent constraints, operations, tasks and
  weight estimators.

  Note: To avoid warnings about replacing clojure.core/name
  its best to require this file qualified or under an alias,
  or if you are defining a configuration in a dedicated file
  just add (:refer-clojure :exclude [name]) to the ns declaration."
  (:refer-clojure :exclude [name])
  (:require [sre.plan.op :refer :all])
  (:import (clojure.lang IPersistentSet)
           (sre.plan.op OpBinding Op)))

(defprotocol IConfig
  "Configuration used to store constraints, operations, etc."
  (name [this] "Returns the name of this config ")
  (constraints [this] "Constraints provided by this config")
  (operations [this] "Operations provided by this config")
  (weight [this] "Operation weight estimator provided by this config")
  (tasks [this] "Tasks provided by this config"))

(def ^:private dispatch-op (fn ^Op [^OpBinding op-binding & rest] (optype op-binding)))

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
       (defmulti ^:private ~'-weight ~dispatch-op)
       (defmulti ^:private ~'-tasks ~dispatch-op)

       (def ~name (reify IConfig
                    (name [~'this] ~'-name)
                    (constraints [~'this] ~'-constraints)
                    (operations [~'this] ~'-ops)
                    (weight [~'this] ~'-weight)
                    (tasks [~'this] ~'-tasks)))

       (defn ~(symbol (str factory-prefix "getInstance")) [] ~name)
       (gen-class :name ~factory-name
                  :prefix ~factory-prefix
                  :methods [^:static [~'getInstance [] sre.plan.config.IConfig]]))))
