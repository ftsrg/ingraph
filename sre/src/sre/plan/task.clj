(ns sre.plan.task
  (:refer-clojure :exclude [name])
  (:require [clojure.pprint :as pprint]))

(defprotocol ISearch
  (search [this bindings variables ctx] "Given the variable bindings, the variable lookup and
  any context, executes the underlying search and returns a (preferable lazy!!!) sequence of
  the variable lookups marking the current head nodes of the search tree. In case there is no
  match returns an empty sequence (not nil)."))

(defprotocol IPrettyTask (name [this]))

(defn pprint-task [type]
  (pprint/pprint-logical-block :prefix "<" :suffix ">"
                               (pprint/write-out (-> type name resolve meta :name))))

(defmethod pprint/simple-dispatch sre.plan.task.IPrettyTask [type] (pprint-task type))

(defmacro deftask
  "Generates an ISearch implementation for an Op. 'body' is the body of the
  'search' which will have in its scope all the parameter bindings of that method,
  additionally to 'op', this macro's first parameter."
  [op & body]
  (if (contains? (ns-interns *ns*) '-tasks)
    `(defmethod ~'-tasks ~op [~'op]
       (reify
         IPrettyTask (name [~'this] (:name ~'op))
         ISearch (search [~'this ~'bindings ~'variables ~'ctx] ~@body)))))
