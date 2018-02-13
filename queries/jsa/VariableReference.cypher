MATCH
    (variable:Variable)-[:_qualifier]->(qualifier:Qualifier),
    (variable)-[:references]->(:Reference)-[:node]->(variableReference:VariableReference)

MERGE
    (variableReference)-[:_qualifier]->(qualifier)
