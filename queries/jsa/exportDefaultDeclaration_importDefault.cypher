MATCH
// exporter.js: export default name1;
    (exporter:CompilationUnit)-[:contains]->(:ExportDefault)
        -[:body]->(:FunctionDeclarationClassDeclarationVariableDeclaration)
        -[:name]->(exportBindingIdentifier:BindingIdentifier)<-[:node]-(declarationToMerge:Declaration)
        <-[:declarations]-(exportedVariable:Variable),

// importer.js: import defaultName from "exporter";
    (importer:CompilationUnit)-[:contains]->(import:Import)
        -[:defaultBinding]->(importBindingIdentifierToMerge:BindingIdentifier)
        <-[:node]-(declarationToDelete:Declaration)<-[:declarations]-(importedVariable:Variable)

    WHERE
    exporter.parsedFilePath CONTAINS import.moduleSpecifier

MERGE
    (importedVariable)-[:declarations]->(declarationToMerge)-[:node]->(importBindingIdentifierToMerge)

DETACH DELETE
declarationToDelete
;
