#!/bin/bash

rm -rf graphmls
mkdir graphmls

for i in 01 04 07 08 17; do
  echo ================================================================================
  echo Procesing data set for query $i.
  echo ================================================================================

  # delete previous database for $i
  ./delete-neo4j-database.sh

  # import, restart and wait
  export DATA_DIR=`pwd`/$i
  ./import-to-neo4j.sh
  $NEO4J_HOME/bin/neo4j restart
  sleep 5

  $NEO4J_HOME/bin/cypher-shell -u neo4j -p admin "CALL apoc.export.graphml.all('graphmls/bi-$i.graphml', {useTypes: true})"
done
