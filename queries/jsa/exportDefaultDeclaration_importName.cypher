MATCH
// exporter.js: export default name1;
    (exporter:CompilationUnit)-[:contains]->(:ExportDefault)
        -[:body]->(:FunctionDeclarationClassDeclarationVariableDeclaration)
        -[:name]->(exportBindingIdentifier:BindingIdentifier)<-[:node]-(declarationToMerge:Declaration)
        <-[:declarations]-(exportedVariable:Variable),

// importer.js: import { name1 } from "exporter";
    (importer:CompilationUnit)-[:contains]->(import:Import)
        -[:namedImports]->(importSpecifier:ImportSpecifier)
        -[:binding]->(importBindingIdentifierToMerge:BindingIdentifier)
        <-[:node]-(declarationToDelete:Declaration)<-[:declarations]-(importedVariable:Variable)

    WHERE
    exporter.parsedFilePath CONTAINS import.moduleSpecifier
    AND importBindingIdentifierToMerge.name = exportBindingIdentifier.name

MERGE
    (importedVariable)-[:declarations]->(declarationToMerge)-[:node]->(importBindingIdentifierToMerge)

DETACH DELETE
declarationToDelete
;
