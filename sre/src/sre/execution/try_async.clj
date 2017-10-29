(ns my-first-clojure-go-routine
  (:require [clojure.core.async :refer :all]))

(defn ping [name chan & [start?]]
  (go (when start? (>! chan 0))
      (loop [i (<! chan)]
        (println name i)
        (>! chan (inc i))
        (if (< i 100) (recur (<! chan)) i))))

(def c (chan))

(ping "Alice" c :start!!!)
(ping "Bob" c)
