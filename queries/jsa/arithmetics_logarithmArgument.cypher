MATCH
    (containingCompilationUnit:CompilationUnit)-[:contains]->(callExpression:CallExpression)
        -[:callee]->(memberExpression:StaticMemberExpression)-[:object]->(variableReference:VariableReference),
    (callExpression)-[:arguments]->(:VariableReference)<-[:node]-(:Reference)<-[:references]-(:Variable)
        -[:declarations]->(:Declaration)-[:node]->(:VariableReference)<-[:binding]-(:VariableDeclarator)
        -[:init]->(unaryExpression:UnaryExpression)-[:operand]->(:LiteralNumericExpression),
    (callExpression)-[:location]->(:SourceSpan)-[:start]->(entityLocation:SourceLocation)

    WHERE
    variableReference.name = 'Math'
    AND memberExpression.property = 'log'
    AND unaryExpression.operator = 'Minus'

RETURN
    'Logarithm called with negative argument' AS message,
    '' AS entityName,
    containingCompilationUnit.parsedFilePath AS compilationUnitPath,
    entityLocation.line AS line,
    entityLocation.column AS column
;
