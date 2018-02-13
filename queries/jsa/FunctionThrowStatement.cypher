MATCH
    (function:Function)-[:body]->(:FunctionBody)-[:statements]->(:ThrowStatement)
        -[:_qualifier]->(qualifier:Qualifier)

MERGE
    (function)-[:_qualifier]->(qualifier)
