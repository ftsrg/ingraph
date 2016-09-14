#!/bin/bash

../../openCypher/tools/grammar/src/main/shell/launch.sh Antlr4 cypher.xml > Cypher.g4
../../openCypher/tools/grammar/src/main/shell/launch.sh Antlr4 --INCLUDE_LEGACY=true cypher.xml | sed -e 's/^grammar Cypher;$/grammar CypherLegacy;/' > CypherLegacy.g4

java -jar ../antlr/antlr-4.5.3-complete.jar -package ingraph.antlr -visitor Cypher.g4
patch -p1 < CypherParser.patch
mv -t src/main/java/ingraph/antlr/ *.java
rm *.tokens
