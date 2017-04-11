#!/bin/bash

while true; do
  cd ~/git/ingraph
  gradle clean distzip && cd ingraph-optimization-tests/build/distributions/ && unzip ingraph-optimization-tests-0.2.0.zip

  cd ingraph-optimization-tests-0.2.0

  for i in `seq 0 9`; do
    echo $i
    bin/ingraph-optimization-tests > /dev/null 2> output.log
    if ! grep sortAndTopOperator output.log; then
      echo error found
      exit
    fi
  done
done
