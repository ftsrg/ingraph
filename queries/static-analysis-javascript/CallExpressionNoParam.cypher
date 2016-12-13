MATCH
  (call:CallExpression)-[:callee]->(:IdentifierExpression)
  <-[:node]-(:Reference)<-[:references]-(:Variable)
  -[:declarations]->(:Declaration)-[:node]->(:BindingIdentifier)
  <-[:name]-(fd:FunctionDeclaration),

  (call)    -[:`_end`]->  (callE:End),
  (fd)      -[:`_end`]->  (fdE:End)

WHERE
  NOT (call)-[:arguments]->()

MERGE
  (call)    -[:`_normal`]-> (fd)      -[:`_end`]->
  (fdE)     -[:`_normal`]-> (callE)
