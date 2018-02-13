MATCH
// exporter.js: export { name1 as exportedName1 };
    (exporter:CompilationUnit)-[:contains]->(:ExportLocals)-[:namedExports]->(exportLocalSpecifier:ExportLocalSpecifier)
        -[:name]->(:IdentifierExpression)<-[:node]-(:Reference)<-[:references]-(:Variable)
        -[:declarations]->(declarationToMerge:Declaration)-[:node]->(:BindingIdentifier),

// importer.js: import { exportedName1 as importedName1 } from "exporter";
    (importer:CompilationUnit)-[:contains]->(import:Import)-[:namedImports]->(importSpecifier:ImportSpecifier)
        -[:binding]->(importBindingIdentifierToMerge:BindingIdentifier)<-[:node]-(declarationToDelete:Declaration)
        <-[:declarations]-(importedVariable:Variable)

    WHERE
    exporter.parsedFilePath CONTAINS import.moduleSpecifier
    AND exportLocalSpecifier.exportedName = importSpecifier.name

MERGE
    (importedVariable)-[:declarations]->(declarationToMerge)-[:node]->(importBindingIdentifierToMerge)

DETACH DELETE
declarationToDelete
;
