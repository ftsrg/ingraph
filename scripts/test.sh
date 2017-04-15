#!/bin/bash

set -e

cd "$( cd "$( dirname "$0" )" && pwd )/../"

./gradlew :ire:test :ingraph-expression-parser:test :ingraph-ire:test :ingraph-relalg2tex:test "$@" --continue --stacktrace
