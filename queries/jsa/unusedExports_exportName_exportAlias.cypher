MATCH
    (exporter:CompilationUnit)-[:contains]->(:ExportLocals)
        -[:namedExports]->(exportLocalSpecifier:ExportLocalSpecifier)-[:name]->(:VariableReference)
        <-[:node]-(:Reference)<-[:references]-(exportedVariable:Variable),
    (exportLocalSpecifier)-[:location]->(:SourceSpan)-[:start]->(exportLocation:SourceLocation)

    WHERE
    NOT (exportedVariable)-[:declarations]->(:Declaration)-[:node]->(:VariableReference)<-[:binding]-(:ImportSpecifier)

RETURN
    'Globally unused export' AS message,
    exportedVariable.name AS entityName,
    exporter.parsedFilePath AS compilationUnitPath,
    exportLocation.line AS line,
    exportLocation.column AS column
;
