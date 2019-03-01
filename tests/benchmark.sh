#!/bin/bash

RUNS=3
SFS="01 03 1"
QUERIES="2 3 4 6 8 9 12 15 22 23 24"
TOOLS="ingraph"
#TOOLS="ingraph neo4j"

mv ../results/results.csv ../results/results-`date +%s`.csv

echo JAVA_OPTS: $JAVA_OPTS
for SF in $SFS; do
	for TOOL in $TOOLS; do
		for QUERY in $QUERIES; do
			echo scale factor: $SF, tool: $TOOL, query: $QUERY
			timeout 2h ./tests $RUNS $SF $QUERY $TOOL | tee -a ../results/results.csv
		done
	done
done
slack chat send "READY! @ $HOSTNAME" '#incremental'
