(ns sre.plan.task)

(defprotocol ISearch
  (search [this state] "Given a SearchTreeState, executes the underlying search in
  the environment and returns a sequence of the SearchTreeStates holding the result
  of the search."))

(defrecord SearchTreeState [var-lkp env])

(defmacro deftask
  "Generates an ISearch implementation for an op. 'body' is the body of the
  'search' method with parameters named as in the interface.
  In the body will have the following bindings:
  op, this, state"
  [op & body]
  (if (contains? (ns-interns *ns*) '-tasks)
    `(defmethod ~'-tasks
       ~op [~'op]
       (reify ISearch (search [~'this ~'state] ~@body)))))
