MATCH
    (binaryExpression:BinaryExpression)-[:right]->(rightValue:Expression)-[:location]->(:SourceSpan)
        -[:start]->(locationStart:SourceLocation)<-[:contains]-(containingCompilationUnit:CompilationUnit),
    (rightValue)-[:_qualifier]->(equalsZero:EqualsZero)

    WHERE
    binaryExpression.operator = 'Div'

RETURN
    'Division by zero' AS message,
    '' AS entityName,
    containingCompilationUnit.parsedFilePath AS compilationUnitPath,
    locationStart.line AS line,
    locationStart.column AS column
;
