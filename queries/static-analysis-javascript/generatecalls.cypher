MATCH
    // Match called FunctionDeclarations for every CallExpression
    (call:CallExpression)-[:callee]->(:IdentifierExpression)
    <-[:node]-(:Reference)<-[:references]-(:Variable)
    -[:declarations]->(:Declaration)-[:node]->(:BindingIdentifier)
    <-[:name]-(fd:FunctionDeclaration)
MATCH
    // List every call from a function body
    (fun:FunctionDeclaration), (call:CallExpression),
    p = shortestPath((fun)-[*]->(call))

MERGE
    // Create a calls relationship between the caller
    // FunctionDeclaration and the called FunctionDeclaration
    (fun)-[:calls]->(fd)