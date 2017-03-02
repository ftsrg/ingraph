#!/bin/bash

for f in *.feature; do
  grep -o "Scenario: .*" $f | sed -e "s/^/#$f: /"
done > failing-and-regression-tests.txt
