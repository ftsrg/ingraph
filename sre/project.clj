(defproject ingraph/sre "0.4.0-SNAPSHOT"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/algo.generic "0.1.2"]
                 [net.mikera/core.matrix "0.60.3"]
                 [funcool/cats "2.1.0"]]
  :profiles {
             :dev {
                   :dependencies [[org.clojure/test.check "0.9.0"]
                                  [pjstadig/humane-test-output "0.8.3"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.20.0"]
                             [jonase/eastwood "0.2.4"]
                             [nodisassemble "0.1.3"]
                             [org.clojure/tools.trace "0.7.9"]]
                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]}})
