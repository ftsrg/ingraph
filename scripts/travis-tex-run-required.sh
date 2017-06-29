#!/bin/bash

git show --name-only | grep "ingraph-compiler/"
[ "$TRAVIS_BRANCH" = "master" -a "$TRAVIS_PULL_REQUEST" = "false" -a $? -ne 0 ]
