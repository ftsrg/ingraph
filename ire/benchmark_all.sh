#!/bin/bash
for input in $@
do
    echo "----"
    echo $input
    sbt "run-main Queries $input 30" --warn
done
