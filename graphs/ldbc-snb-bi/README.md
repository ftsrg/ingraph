## LDBC test data sets

To use LDBC test data set, run the `link.sh` script to generate the required symlinks.

```bash
./link.sh <dir> <scale factor>
```

Make sure that before you run this, you disable the indexing of the `graphs` directory in IntelliJ. To do so, right click the directory and select **Mark Directory** | **Excluded**.

## Convert small CSVs to GraphMLs

:warning: Before you run any commands, make sure you know what you are doing. These are our internal testing tools and might... _interfere_ with your Neo4j database if the setup is not correct. That is, these scripts will likely wipe your existing Neo4j database without asking any questions.

The goal of these scripts to convert a set of CSVs (e.g. `01/*.csv`) to a single GraphML file, which is often easier to handle.

Follow these steps:

1. Make sure no other Neo4j instance is running.

1. Set the environment variables appropriately:

   ```bash
   export NEO4J_HOME=/your/neo4j/home
   export NEO4J_DB_DIR=$NEO4J_HOME/data/databases/graph.db
   export POSTFIX=_0_0.csv
   ```

1. Run the export. This runs a separate import-database startup-export process for each query, so it might take a while.

   ```bash
   ./convert-to-graphml.sh
   ```

## Producing update streams for benchmarking

Grep the parameters for Interactive updates 2, 3, 5, and 8, use the following command. Adjust the scale factor and the parameter of `head` to the desired values:

```console
cat csv-sf01/updateStream_0_0_forum.csv | grep -E "^[0-9]+?\|[0-9]+?\|(2|3|5|8)\|" | head -n 20
```

or an alternative solution (with different results):

```console
for Q in 2 3 5 8; do cat csv-sf01/updateStream_0_0_forum.csv | grep -E "^[0-9]+?\|[0-9]+?\|$Q\|" | head -n 5; done
```
