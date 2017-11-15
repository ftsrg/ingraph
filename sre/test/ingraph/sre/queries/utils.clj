(ns ingraph.sre.queries.utils
  (:require [sre.plan.pattern :as pattern]
            [taoensso.tufte :as tufte]))

(defn get-tasks [pattern] (((nth pattern 3) (nth pattern 1))))

(defn compile-pattern [def-sym k]
  (let [def @(resolve def-sym)]
    (tufte/p (-> def-sym str keyword) (pattern/compile (:pattern def) (:config def) {:k k}))))

(defn short-name [symbol] (-> symbol resolve meta :name))
