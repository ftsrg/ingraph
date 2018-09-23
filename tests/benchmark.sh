#!/bin/bash

RUNS=3
SFS="01 03 1"
QUERIES="2 3 4 6 8 9 12 15 22 23 24"

mv results.csv results-`date +%s`.csv

for SF in $SFS; do
	for RUN in `seq 1 $RUNS`; do
		for QUERY in $QUERIES; do
			echo scale factor: $SF, run: $RUN, query: $QUERY
			./tests $SF $QUERY | tee results.csv
		done
	done
done
