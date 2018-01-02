#!/bin/bash

for i in $(seq -w 1 25); do

TESTNUM="bi-$i"
TESTTITLE=$(sed -n -e '/^\/\/ Q'$((10#$i))'\./ s/^\/\/ Q[0-9]\+. /: /p' <${TESTNUM}.cypher)
cat <<HERE
  ignore("${TESTNUM} from file${TESTTITLE}") {
    val stages=compileFromFile("${TESTNUM}")
  }

  ignore("bi-$i${TESTTITLE}") {
    val stages = compile(
HERE

    sed -n -e '1 s/^/      """/p' -e '/^$/ d' -e '2,$ s/^/        |/p' <${TESTNUM}.cypher

cat <<HERE
      """.stripMargin)
  }

HERE

done
