#!/bin/bash

# prerequisites: JDK, unzip

export DEBUG_INGRAPH=1

cd "$( cd "$( dirname "$0" )" && pwd )/../"

while true; do
  pushd .
  ./gradlew clean distzip
  cd ingraph-optimization-tests/build/distributions/
  tar xf ingraph-optimization-tests-*.tar

  cd ingraph-optimization-tests-*
  # running the application 30 times
  for i in `seq 0 29`; do
    echo $i
    bin/ingraph-optimization-tests > /dev/null 2> output.log
    if ! grep sortAndTopOperator output.log; then
      echo error found
      cat output.log
      exit
    fi
  done
  pop d.
done
