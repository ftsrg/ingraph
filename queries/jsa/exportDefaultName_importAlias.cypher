MATCH
// exporter.js: export default name1;
    (exporter:CompilationUnit)-[:contains]->(:ExportDefault)
        -[:body]->(exportedIdentifierExpression:IdentifierExpression)<-[:node]-(:Reference)
        <-[:references]-(exportedVariable:Variable)-[:declarations]->(declarationToMerge:Declaration),

// importer.js: import { name1 as importedName1 } from "exporter";
    (importer:CompilationUnit)-[:contains]->(import:Import)-[:namedImports]->(importSpecifier:ImportSpecifier)
        -[:binding]->(importBindingIdentifierToMerge:BindingIdentifier)<-[:node]-(declarationToDelete:Declaration)
        <-[:declarations]-(importedVariable:Variable)

    WHERE
    exporter.parsedFilePath CONTAINS import.moduleSpecifier
    AND exportedIdentifierExpression.name = importSpecifier.name

MERGE
    (importedVariable)-[:declarations]->(declarationToMerge)-[:node]->(importBindingIdentifierToMerge)

DETACH DELETE
declarationToDelete
;
