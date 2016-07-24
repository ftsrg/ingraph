# openCypher

Go to <https://github.com/opencypher/openCypher/> and clone the latest version.

Create the `grammar/generated` directory:

```bash
mkdir grammar/generated
```

Use the following commands to generate the artifacts:


```bash
./tools/grammar/src/main/shell/launch.sh RailRoadDiagramPages -outputDir=grammar/generated/railroad cypher.xml
./tools/grammar/src/main/shell/launch.sh RailRoadDiagrams -DRailRoadDiagrams.inlineNone=true -outputDir=grammar/generated/railroad/raw cypher.xml
./tools/grammar/src/main/shell/launch.sh ISO14977 cypher.xml > grammar/generated/cypher.ebnf
./tools/grammar/src/main/shell/launch.sh Antlr4 cypher.xml > grammar/generated/Cypher.g4
```
