(ns sre.plan.dsl.config
  (:import (clojure.lang IPersistentSet)))

(defmacro defconfig
  "Creates an engine configuration variable in the current namespace, used
  to store constraints, operations, etc., serving as a container for the DSL.
  Similar to an EMF container in concept LOL.

  Interns the vars
   - id: contains the name
   - ops: set of the ops
   - constraints: set of the constraints

  Make sure you only have one defconfig in a namespace, and that it comes
  before all other DSL macro calls!

  Syntax:

  (defconfig name)

  Usage:
  ```
  (ns myconfig (:require
                 [sre.plan.dsl.config
                 [sre.plan.dsl.constraint :refer [defconstraint]]
                 [sre.plan.dsl.op :refer [defop defweight]])))

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
     (def ~'id (str '~name))
     (def ~'ops #{})
     (def ~'constraints #{})
     (defn ~(symbol (str factory-prefix "getOperations")) [] ~'ops)
     (defn ~(symbol (str factory-prefix "getConstraints")) [] ~'constraints)
     (gen-class :name ~factory-name
                :prefix ~factory-prefix
                :methods [^:static [~'getOperations [] IPersistentSet]
                          ^:static [~'getConstraints [] IPersistentSet]]))))
