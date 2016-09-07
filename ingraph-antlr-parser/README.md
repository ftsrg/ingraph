# ANTLR parser

This project contains the generated ANTLR parser classes.

To regenerate the ANTLR recognizer according to the latest specification, perform the following steps.

Go to <https://github.com/opencypher/openCypher/> and clone the latest version.

(Re)build openCypher spec:

```bash
mvn clean install
```

Create the `grammar/generated` directory:

```bash
mkdir -p grammar/generated
```

Use the following commands to generate the artifacts:

```bash
./tools/grammar/src/main/shell/launch.sh RailRoadDiagramPages -outputDir=grammar/generated/railroad cypher.xml
./tools/grammar/src/main/shell/launch.sh RailRoadDiagrams -DRailRoadDiagrams.inlineNone=true -outputDir=grammar/generated/railroad/raw cypher.xml
./tools/grammar/src/main/shell/launch.sh ISO14977 cypher.xml > grammar/generated/cypher.ebnf
./tools/grammar/src/main/shell/launch.sh Antlr4 cypher.xml > grammar/generated/Cypher.g4
./tools/grammar/src/main/shell/launch.sh Antlr4 --INCLUDE_LEGACY=true cypher.xml | sed -e 's/^grammar Cypher;$/grammar CypherLegacy;/' > grammar/generated/CypherLegacy.g4
```

Generate Java parser from the ANTLR4 grammar (currently using antlr4 4.5.3-1 from Debian Stretch) and patch it to refactor `return()` to `return_()`, then move them to the correct place.

```bash
antlr4 -package ingraph.antlr -visitor Cypher.g4
patch -p1 < CypherParser.patch
cp -t src/main/java/ingraph/antlr/ *.java
```
