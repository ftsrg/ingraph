MATCH
    (exporter:CompilationUnit)-[:contains]->(exportDefault:ExportDefault)-[:body]->(:IdentifierExpression)
        <-[:node]-(:Reference)
        <-[:references]-(exportedVariable:Variable),
    (exportDefault)-[:location]->(:SourceSpan)-[:start]->(exportLocation:SourceLocation),

    (exporter:CompilationUnit)-[:contains]->(:ExportLocals)
        -[:namedExports]->(exportLocalSpecifier:ExportLocalSpecifier)-[:name]->(:VariableReference)
        <-[:NODE]-(:Reference)<-[:references]-(exportedVariable:Variable),
    (exportLocalSpecifier)-[:location]->(:SourceSpan) -[:start]->(exportLocation:SourceLocation)

    WHERE
    NOT (exportedVariable)-[:declarations]->(:Declaration)-[:NODE]->(:VariableReference)<-[:binding]-(:ImportSpecifier)

RETURN
    'Globally unused export' AS message,
    exportedVariable.name AS entityName,
    exporter.parsedFilePath AS compilationUnitPath,
    exportLocation.line AS line,
    exportLocation.column AS column
;
