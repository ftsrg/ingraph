# Standalone application for benchmarking


Build the project, and move the files to the root of the repository.

```console
. package.sh
```

Ensure that the JVM will have sufficient memory:

```bash
export JAVA_OPTS="-Xms4G -Xmx12G"
```

To use the SF1 data set and benchmark query 4, run something like the following command. Note that the Neo4j instance has to be stopped beforehand.

```bash
./tests 1 4 ~/neo4j/data/databases/graph.db
```

To run ingraph, simply omit the Neo4j directory.
