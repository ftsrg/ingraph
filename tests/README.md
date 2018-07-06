# Standalone application for benchmarking


Build the project, and move the files to the root of the repository.

```console
cd $(git rev-parse --show-toplevel) && \
  rm -rf lib/ bin/ && \
  ./gradlew clean build installDist -x test && \
  cp -R tests/build/install/tests/. . && \
  cd bin
```

Ensure that the JVM will have sufficient memory by using:

```bash
export JAVA_OPTS="-Xms4G -Xmx12G"
```

To use the SF1 data set and benchmark query 4, run something like the following command. Note that the Neo4j instance has to be stopped beforehand.

```bash
./tests 1 4 ~/neo4j/data/databases/graph.db
```

To run ingraph, simply omit the Neo4j directory.
