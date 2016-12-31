#!/bin/bash

set -e # exit with nonzero exit code if anything fails

SLIZAA_COMMIT_ID=664a6df2b8c86344d13956fcd27efadde470371f

cd "$( cd "$( dirname "$0" )" && pwd )/../../"
git clone https://github.com/slizaa/slizaa-opencypher-xtext/ || true
cd slizaa-opencypher-xtext
git checkout $SLIZAA_COMMIT_ID
echo "Building slizaa-opencypher-xtext in quiet mode (only prints to console for the Xtext generator and in case of errors)"
mvn clean install -q -DskipTests
