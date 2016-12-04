#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../../"
git clone https://github.com/slizaa/slizaa-opencypher-xtext/
cd slizaa-opencypher-xtext
mvn clean install -q -DskipTests
