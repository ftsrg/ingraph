#!/bin/bash

for i in `seq -w 1 25`; do

TESTNAME="bi-$i"
cat <<HERE
  ignore("${TESTNAME} from file") {
    val stages=compileFromFile("${TESTNAME}")
  }

  ignore("bi-$i") {
    val stages = compile(
HERE

    cat ${TESTNAME}.cypher | sed -n -e '1 s/^/      """/p' -e '/^$/ d' -e '2,$ s/^/        |/p'

cat <<HERE
      """.stripMargin)
  }

HERE

done
