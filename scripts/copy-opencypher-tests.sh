#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../"
find ../openCypher/ -name "*.feature" | xargs -I {} cp {} opencypher-tests/
