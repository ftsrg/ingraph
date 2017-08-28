(defproject ingraph/sre-adapter "0.4.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ingraph/sre "0.2.0-SNAPSHOT"]
                 [ingraph/ire-adapter "0.2.0-SNAPSHOT"]]
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resource"]
  :repositories [["acceleo" "https://repo.eclipse.org/content/groups/acceleo"]
                 ["viatra2" "https://repo.eclipse.org/content/groups/viatra2"]
                 ["ingraph-deps" "https://szdavid92.github.io/ingraph-deps"]]
  :profiles {
             :dev {
                   :dependencies [[org.clojure/test.check "0.9.0"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.20.0"]
                             [jonase/eastwood "0.2.4"]]}}
  :aot :all)
