MATCH
    (variable:Variable)-[:declarations]->(:Declaration)-[:node]->(:BindingIdentifier)
        -[:_qualifier]->(qualifier:Qualifier)

MERGE
    (variable)-[:_qualifier]->(qualifier)
