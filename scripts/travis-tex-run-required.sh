#!/bin/bash
 
# 'grep' returns with 0 if there was a match
git show --name-only | grep -q "\(ingraph-compiler\|opencypher-report\)/"
GREP_RETVAL=$?
# we only build on Travis if we are on the master branch, it is not a pull request and the compiler/report changed
[ "$TRAVIS_BRANCH" = "master" -a "$TRAVIS_PULL_REQUEST" = "false" -a ${GREP_RETVAL} = 0 ]
