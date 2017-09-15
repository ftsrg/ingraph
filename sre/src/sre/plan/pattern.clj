(ns sre.plan.pattern
  (:require [sre.plan.dsl.constraint :refer :all])
  (:import (sre.plan.dsl.constraint ConstraintBinding)))

(defprotocol IPattern
  (flatten [this outer]))

(extend-type ConstraintBinding)

(defn constraint [vars type]
  (->ConstraintBinding type var))

(defrecord CompositePattern [requires satisfies patterns])



(defrecord OrPattern [patterns])

(defrecord NotPattern [pattern])
