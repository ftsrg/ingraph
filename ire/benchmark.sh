#!/bin/bash -xue

cd $(dirname $0)

for commit in $(cat benchmarkresults/interesting.txt)
do
  csv=benchmarkresults/$commit.txt
  if [ ! -e $csv ]
  then
    git checkout $commit
    gradle clean jmhJar
    java -jar build/libs/ire-jmh.jar -gc true -rf csv -rff benchmarkresults/`git rev-parse HEAD`.csv
  fi
done
