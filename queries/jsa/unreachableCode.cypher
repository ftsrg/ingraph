MATCH
    (containingCompilationUnit:CompilationUnit)-[:contains]->(statement:Statement)-[:_qualifier]->(:ExceptionThrown),
    (statement)-[:_next]->(unreachableStatement:Statement),
    (unreachableStatement)-[:location]->(:SourceSpan)-[:start]->(entityLocation:SourceLocation)

RETURN
    'Unreachable code' AS message,
    '' AS entityName,
    containingCompilationUnit.parsedFilePath AS compilationUnitPath,
    entityLocation.line AS line,
    entityLocation.column AS column
;
