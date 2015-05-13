#!/bin/bash
for input in $@
do
    echo "----"
    echo $input
    out=${input:0:-3}bin
    sbt "run-main Serializer $input $out" --warn
done
