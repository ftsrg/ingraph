(defproject ingraph-engine-ingraph-sre "0.2.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ingraph-engine/sre "0.2.0-SNAPSHOT"]
                 [ingraph-engine/ingraph-ire "0.2.0-SNAPSHOT"]]
  :source-paths ["src" "src/main/clj"]
  :java-source-paths ["src/main/java"]  ; Java source is stored separately.
  :test-paths ["test" "src/test/clj"]
  :resource-paths ["src/main/resource"]
  :repositories [["acceleo" "https://repo.eclipse.org/content/groups/acceleo"]
                 ["viatra2" "https://repo.eclipse.org/content/groups/viatra2"]
                 ["ingraph-deps" "https://szdavid92.github.io/ingraph-deps"]])
