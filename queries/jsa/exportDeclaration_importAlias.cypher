MATCH
// exporter.js: export var name1;
    (exporter:CompilationUnit)-[:contains]->(:ExportDeclaration)
        -[:declaration]->(:FunctionDeclarationClassDeclarationVariableDeclaration)-[:declarators]->(:VariableDeclarator)
        -[:binding]->(exportBindingIdentifier:BindingIdentifier)<-[:node]-(declarationToMerge:Declaration)
        <-[:declarations]-(:Variable),

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
