MATCH
    (exporter:CompilationUnit)-[:contains]->(exportDeclaration:ExportDeclaration)
        -[:declaration]->(:FunctionDeclarationClassDeclarationVariableDeclaration)-[*1..2]->(:BindingIdentifier)
        <-[:node]-(:Declaration)<-[:declarations]-(exportedVariable:Variable),
    (exportDeclaration)-[:location]->(:SourceSpan)-[:start]->(exportLocation:SourceLocation)

    WHERE
    NOT (exportedVariable)-[:declarations]->(:Declaration)-[:node]->(:VariableReference)<-[:binding]-(:ImportSpecifier)

RETURN
    'Globally unused export' AS message,
    exportedVariable.name AS entityName,
    exporter.parsedFilePath AS compilationUnitPath,
    exportLocation.line AS line,
    exportLocation.column AS column
;
