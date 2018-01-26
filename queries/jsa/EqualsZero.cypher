MATCH
    (literalNumericExpression:LiteralNumericExpression),
    (qs:QualifierSystem)-[:_instance]->(equalsZero:Qualifier:EqualsZero)

    WHERE
    literalNumericExpression.value = 0

MERGE
    (literalNumericExpression)-[:_qualifier]->(equalsZero)
