(ns sre.execution.dsl)

(defprotocol Task
  (call [this env var-lkp] "Execute the task"))

(defprotocol Environment
  (create-task [this op] "Create environment specific task from an operation")
  (call-task [this task var-lkp] "Execute environment specific task"))

;; Beware! Very opinionated and specialized macros below. Whether you use them or not is up to you.
;; Clearly composability and extendability is traded for conciseness, please don't hate.

(defmacro defenv
  "Generates boilerplate for creating an environment implementation. It creates a deftype
  with the given name, fields and opts+specs. It will implement the Environment protocol
  for you so don't need to do it manually. This is done by creating a multimethod dispatch-create-task,
  that will be called by create-task to dispatch to the corresponding op. You can add method
  implementations with deftask. Creates a def 'env' with the class, used for type hinting
  env in the call method of deftask.

  (defenv name [&fields] & opts+specs)

  There should be only 1 defenv in a namespace!"
  [name fields & opts+specs]
  `(do
     (defmulti ~'dispatch-create-task (fn [~'op & ~'rest] (:name ~'op)))
     (deftype ~name
       ~fields
       ~@opts+specs
       Environment
       (create-task [~'this ~'op] (~'dispatch-create-task ~'op ~'this))
       (call-task [~'this ~'task ~'var-lkp] (call ~'task ~'this ~'var-lkp)))
     ; used for type hint in deftask
     (def ~'env ~name)))

(defmacro deftask
  "Generates a Task implementation and a method for creating it from an op. 'body' is the body of the
  'call' method with parameters named as in the interface."
  [task op & body]
  `(do
     (defrecord ~task [~'vars] Task (call [~'this ^{:tag ~(deref (resolve 'env))} ~'env ~'var-lkp] ~@body))
     (defmethod ~'dispatch-create-task ~op [~'op ~'env]
       (new ~task (:vars ~'op)))))
