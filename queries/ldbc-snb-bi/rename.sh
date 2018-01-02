#!/bin/bash

for i in $(seq 1 25)
do
    mv bi-$i.cypher bi-$(printf %02d ${i}).cypher
done
