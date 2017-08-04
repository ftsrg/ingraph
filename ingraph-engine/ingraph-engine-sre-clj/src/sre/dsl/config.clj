(ns sre.dsl.config)

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
                 [sre.dsl.config
                 [sre.dsl.constraint :refer [defconstraint]]
                 [sre.dsl.op :refer [defop defweight]])))

  (defconfig MyConfig)

  (defconstraint Constraint01)
  ...

  (defop Op01)
  ```

  Now you can get all ops from myconfig/ops etc."


  [name]
  `(do
     (def ~'id (str '~name))
     (def ~'ops #{})
     (def ~'constraints #{})))
