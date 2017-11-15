(ns ingraph.sre.queries.utils.tb-loader
  (:refer-clojure :exclude [load])
  (:require [clojure.java.io :as io])
  (:import ingraph.bulkloader.csv.loader.MassCsvLoader
           ingraph.ire.Indexer))

(defn- csv-file-for [ts name] (.getPath (io/file "graphs" "trainbenchmark"
                                                 (str "railway-" ts "-" name ".csv"))))

(defn- node-filenames [ts]
  {(csv-file-for ts "Region") ["Region"]
   (csv-file-for ts "Route") ["Route"]
   (csv-file-for ts "Segment") ["Segment" "TrackElement"]
   (csv-file-for ts "Semaphore") ["Semaphore"]
   (csv-file-for ts "Sensor") ["Sensor"]
   (csv-file-for ts "Switch") ["Switch" "TrackElement"]
   (csv-file-for ts "SwitchPosition") ["SwitchPosition"]})

(defn- relationship-filenames [ts]
  {(csv-file-for ts "connectsTo") "connectsTo",
   (csv-file-for ts "entry") "entry",
   (csv-file-for ts "exit") "exit",
   (csv-file-for ts "follows") "follows",
   (csv-file-for ts "monitoredBy") "monitoredBy"
   (csv-file-for ts "requires") "requires"
   (csv-file-for ts "target") "target"})

(defn load
  ([ts] (let [indexer (Indexer.)
              loader (MassCsvLoader. (node-filenames ts) (relationship-filenames ts))]
          (doseq [node (.getNodes loader)] (.addVertex indexer node))
          (doseq [edge (.getRelationships loader)] (.addEdge indexer edge))
          indexer))
  ([] (load 'inject-1)))
