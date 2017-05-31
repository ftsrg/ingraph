#!/bin/bash

$NEO4J_HOME/bin/neo4j-import --into $DB_DIR \
  --nodes:Person person$POSTFIX \
  --relationships:knows person_knows_person$POSTFIX \
  --delimiter '|'
