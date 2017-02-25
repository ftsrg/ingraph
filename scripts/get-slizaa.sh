#!/bin/bash

set -e # exit with nonzero exit code if anything fails

SLIZAA_COMMIT_ID=c1f3e4443239e320e1e9c7d06ac326b5a8955524

cd "$( cd "$( dirname "$0" )" && pwd )/../../"
git clone https://github.com/slizaa/slizaa-opencypher-xtext/ || true
cd slizaa-opencypher-xtext
git fetch
git checkout $SLIZAA_COMMIT_ID
echo "Building slizaa-opencypher-xtext in quiet mode (only prints to console for the Xtext generator and in case of errors)"
time mvn clean install -q -DskipTests
