#!/bin/bash

SLIZAA_COMMIT_ID=664a6df2b8c86344d13956fcd27efadde470371f

cd "$( cd "$( dirname "$0" )" && pwd )/../../"
git clone https://github.com/slizaa/slizaa-opencypher-xtext/
cd slizaa-opencypher-xtext
git checkout $SLIZAA_COMMIT_ID
mvn clean install -q -DskipTests
