#!/bin/bash

RUNS=3
SFS="01 03 1"
QUERIES="2 3 4 6 8 9 12 15 22 23 24"

mv results.csv results-`date +%s`.csv

for SF in $SFS; do
	echo scale factor: $SF
	for RUN in $RUNS; do
		echo run: $RUN
		for QUERY in $QUERIES; do
			echo query: $QUERY
			./tests $SF $QUERY | tee results.csv
		done
	done
done
