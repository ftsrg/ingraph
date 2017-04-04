#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../"

./gradlew :ire:test --continue
./gradlew :ingraph-expression-parser:test --continue
./gradlew :ingraph-ire:test --continue
./gradlew :ingraph-relalg2tex:test --continue
