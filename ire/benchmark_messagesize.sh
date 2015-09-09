#!/bin/bash
for input in ${@:2}
do
    echo "----"
    echo $input
    sbt "run-main Queries $1 $input" --warn
done
