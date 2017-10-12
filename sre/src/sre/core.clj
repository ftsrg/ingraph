(ns sre.core
  (:require [clojure.pprint :as pprint])
  (:import (java.io Writer)))

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

(defmethod pprint/simple-dispatch Binding [b] (pprint-binding b))
