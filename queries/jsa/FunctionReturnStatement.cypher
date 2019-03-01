MATCH
    (function:Function)-[:body]->(:FunctionBody)-[:statements]->(:ReturnStatement)-[:expression]->(:Expression)
        -[:_qualifier]->(qualifier:Qualifier)

MERGE
    (function)-[:_qualifier]->(qualifier)
