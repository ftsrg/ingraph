MATCH
// exporter.js: let name1 = "name1Value"; export { name1 };
    (exporter:CompilationUnit)-[:contains]->(:ExportLocals)-[:namedExports]->(:ExportLocalSpecifier)
        -[:name]->(exportBindingIdentifier:IdentifierExpression)<-[:node]-(:Reference)<-[:references]-(:Variable)
        -[:declarations]->(declarationToMerge:Declaration)-[:node]->(:BindingIdentifier),

// importer.js: import { name1 as importedName1 } from "exporter";
    (importer:CompilationUnit)-[:contains]->(import:Import)-[:namedImports]->(importSpecifier:ImportSpecifier)
        -[:binding]->(importBindingIdentifierToMerge:BindingIdentifier)<-[:node]-(declarationToDelete:Declaration)
        <-[:declarations]-(importedVariable:Variable)

    WHERE
    exporter.parsedFilePath CONTAINS import.moduleSpecifier
    AND exportBindingIdentifier.name = importSpecifier.name

MERGE
    (importedVariable)-[:declarations]->(declarationToMerge)-[:node]->(importBindingIdentifierToMerge)

DETACH DELETE
declarationToDelete
;
