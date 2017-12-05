#!/bin/bash

rm -rf graphmls
mkdir graphmls

for i in 0{1..9} {10..25} ; do
  echo ================================================================================
  echo Procesing data set for query $i.
  echo ================================================================================

  # delete previous database for $i
  ./delete-neo4j-database.sh

  # import, restart and wait
  export DATA_DIR=`pwd`/$i
  ./import-to-neo4j.sh
  $NEO4J_HOME/bin/neo4j restart
  # 5 seconds of sleep was not enough and i7/SSD laptop
  sleep 10

  $NEO4J_HOME/bin/cypher-shell -u neo4j -p admin "CALL apoc.export.graphml.all('graphmls/bi-$i.graphml', {useTypes: true})"
done
