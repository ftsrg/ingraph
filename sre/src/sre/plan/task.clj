(ns sre.plan.task)

(defprotocol ISearch
  (search [this bindings variables ctx] "Given the variable bindings, the variable lookup and
  any context, executes the underlying search and returns a (preferable lazy!!!) sequence of
  the variable lookups marking the current head nodes of the search tree. In case there is no
  match returns an empty sequence (not nil)."))

(defmacro deftask
  "Generates an ISearch implementation for an Op. 'body' is the body of the
  'search' which will have in its scope all the parameter bindings of that method,
  additionally to 'op', this macro's first parameter."
  [op & body]
  (if (contains? (ns-interns *ns*) '-tasks)
    `(defmethod ~'-tasks ~op [~'op]
       (reify ISearch
         (search [~'this ~'bindings ~'variables ~'ctx] ~@body)))))
