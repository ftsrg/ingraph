(ns sre.core
  (:require [clojure
             [pprint :as pprint]
             [string :as str]])
  (:refer-clojure :exclude [name])
  (:import java.io.Writer))

(defprotocol INamed
  (name [this]))

(defprotocol IBind
  "Something that can be bound to values"
  (bind [this vals])
  (bind-map [this val-map] "Binds this to the given variable key-value mapping"))

(defprotocol IConfig
  "Configuration used to store constraints, operations, etc."
  (constraints [this] "Constraints provided by this config")
  (operations [this] "Operations provided by this config")
  (costs [this] "Operation cost estimator provided by this config")
  (tasks [this] "Tasks provided by this config")
  (cost-calculator [this] "Cost calculator used by this config"))


(defrecord Binding [type bindings])

(def variable? keyword?)
(def constant? (comp not variable?))

(defn pprint-binding [b]
  (pprint/pprint-logical-block
   :prefix "<" :suffix ">"
   (pprint/write-out (:type b))
   (.write ^Writer *out* "::")
   (pprint/write-out (:bindings b))))

(defn- short-name [name]
  (let [name (str name)
        start (str/last-index-of name "/")]
    (symbol (subs name (if (nil? start) 0 (inc start))))))

(defn pprint-short-name [name]
  (pprint/pprint-logical-block :prefix "<" :suffix ">" (pprint/write-out (short-name name))))

(defmethod pprint/simple-dispatch Binding [b] (pprint-binding b))
