(ns ingraph.sre.queries.routesensorpositive-pattern
  (:require [ingraph.sre
             [ingraph-proto-1 :as proto-1]
             [ingraph-proto-2 :as proto-2]]
            [sre.core :refer [bind]]
            [sre.plan.pattern :as pattern]))

(def p-1
  {:config proto-1/Ingraph
   :pattern (pattern/make-pattern [:route :follows :sw-p :target :sw :monitored-by :sensor] []
                                  [(bind proto-1/DirectedEdge [:route :follows :sw-p])
                                   (bind proto-1/DirectedEdge [:sw-p :target :sw])
                                   (bind proto-1/DirectedEdge [:sw :monitored-by :sensor])
                                   (bind proto-1/HasLabels [:route :-route-label])
                                   (bind proto-1/HasType [:follows :-follows-type])
                                   (bind proto-1/HasLabels [:sw-p :-sw-p-label])
                                   (bind proto-1/HasType [:target :-target-type])
                                   (bind proto-1/HasLabels [:sw :-switch-label])
                                   (bind proto-1/HasType [:monitored-by :-monitored-by-type])
                                   (bind proto-1/HasLabels [:sensor :-sensor-label])
                                   (bind proto-1/Constant [:-route-label "Route"])
                                   (bind proto-1/Constant [:-follows-type "follows"])
                                   (bind proto-1/Constant [:-sw-p-label "SwitchPosition"])
                                   (bind proto-1/Constant [:-target-type "target"])
                                   (bind proto-1/Constant [:-switch-label "Switch"])
                                   (bind proto-1/Constant [:-monitored-by-type "monitoredBy"])
                                   (bind proto-1/Constant [:-sensor-label "Sensor"])])})
(def p-2
  {:config proto-2/Ingraph
   :pattern (pattern/make-pattern [:route :follows :sw-p :target :sw :monitored-by :sensor] []
                                  [(bind proto-2/DirectedEdge [:route :follows :sw-p])
                                   (bind proto-2/DirectedEdge [:sw-p :target :sw])
                                   (bind proto-2/DirectedEdge [:sw :monitored-by :sensor])
                                   (bind proto-2/HasLabels [:route :-route-label])
                                   (bind proto-2/HasType [:follows :-follows-type])
                                   (bind proto-2/HasLabels [:sw-p :-sw-p-label])
                                   (bind proto-2/HasType [:target :-target-type])
                                   (bind proto-2/HasLabels [:sw :-switch-label])
                                   (bind proto-2/HasType [:monitored-by :-monitored-by-type])
                                   (bind proto-2/HasLabels [:sensor :-sensor-label])
                                   (bind proto-2/Constant [:-route-label "Route"])
                                   (bind proto-2/Constant [:-follows-type "follows"])
                                   (bind proto-2/Constant [:-sw-p-label "SwitchPosition"])
                                   (bind proto-2/Constant [:-target-type "target"])
                                   (bind proto-2/Constant [:-switch-label "Switch"])
                                   (bind proto-2/Constant [:-monitored-by-type "monitoredBy"])
                                   (bind proto-2/Constant [:-sensor-label "Sensor"])])})
