## Preprocessing

Set the `$NEO4J_HOME` and the following environment variables appropriately:

```bash
export POSTFIX=_0_0.csv
export DB_DIR=$NEO4J_HOME/data/databases/graph.db
```

### Script for converting city/country/continent to City/Country/Continent

```bash
sed -i "s/|c\([a-z]\+\)$/|C\1/" place$POSTFIX
```

### Delete your database and load the SNB CSVs

Be careful - this deletes all data in your database.

```bash
./delete-neo4j-database.sh
./replace-headers.sh
./import-to-neo4j.sh
```

Restart Neo4j.

## Stuff that we currently ignore

We ignore some files as they are difficult to load and none of the queries need them.

### Node files

* organisation

### Relationship files

* person_studyAt_organisation
* person_workAt_organisation
* organisation_isLocatedIn_place

### Property files

All of them:

* person_speaks_language
* person_email_emailaddress

## Misc

Regex for extracting relationship names (Atom):

`(\w+)_(\w+)_(\w+)` -> `--relationship:$2 $1_$2_$3`
