MATCH
    (expression:Expression)-[:_qualifier]->(qualifier:Qualifier),
    (expression)<-[:init]-(:VariableDeclarator)-[:binding]->(:BindingIdentifier)<-[:node]-(:Reference)
        <-[:references]-(variable:Variable)

MERGE
    (variable)-[:_qualifier]->(qualifier)
