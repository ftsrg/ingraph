(ns sre.core
  (:require [clojure
             [pprint :as pprint]
             [string :as str]])
  (:import java.io.Writer))

(defprotocol IBind
  "Something that can be bound to values"
  (bind [this vals])
  (bind-map [this val-map] "Binds this to the given variable key-value mapping"))

(defrecord Binding [type bindings])

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
