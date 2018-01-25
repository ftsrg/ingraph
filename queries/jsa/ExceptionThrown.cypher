MATCH
    (throwStatement:ThrowStatement),
    (qs:QualifierSystem)-[:_instance]->(exceptionThrown:ExceptionThrown)

MERGE
    (throwStatement)-[:_qualifier]->(exceptionThrown)
