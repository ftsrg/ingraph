#!/bin/bash
for input in $@
do
    echo "----"
    echo $input
	len=$((${#input}-3))
    out=${input:0:$len}bin
    sbt "run-main Serializer $input $out" --warn
done
