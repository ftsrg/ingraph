#!/bin/bash

FEATURE=$1

for i in `seq -w 1 25`; do
  if grep -q "$FEATURE" bi-$i.cypher; then
    echo x
  else
    echo
  fi
done
