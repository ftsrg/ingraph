MATCH
    (containingCompilationUnit:CompilationUnit)-[:contains]->(variableLocation:SourceLocation)<-[:start]-(:SourceSpan)
        <-[:location]-(variableReference:VariableReference)<-[:node]-(:Reference)
        <-[:references]-(subjectVariable:Variable)-[:declarations]->(:Declaration)-[:node]->(:VariableReference)
        <-[:binding]-(variableDeclarator:VariableDeclarator)

    WHERE NOT (variableDeclarator)-[:init]->()

RETURN
    'Non-initialized variable' AS message,
    subjectVariable.name AS entityName,
    containingCompilationUnit.parsedFilePath AS compilationUnitPath,
    variableLocation.line AS line,
    variableLocation.column AS column
;
