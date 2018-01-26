MATCH
    (callExpression:CallExpression)-[:callee]->(:Expression)-[:_qualifier]->(qualifier:Qualifier)

MERGE
    (callExpression)-[:_qualifier]->(qualifier)
