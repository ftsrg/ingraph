#!/bin/bash

./gradlew clean assemble testClasses :ingraph-engine:ingraph-engine-ire:test :ingraph-engine:ingraph-engine-expression-parser:test :ingraph-engine:ingraph-engine-ingraph-ire:test  --stacktrace
