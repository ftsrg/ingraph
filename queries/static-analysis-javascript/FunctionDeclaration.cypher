MATCH
  (fd:FunctionDeclaration)-[:body]->(b:FunctionBody)-[:statements]->(list:List),

  (fd)    -[:`_end`]->  (fdE:End),
  (list)  -[:`_end`]->  (listE:End)

MERGE
  (fd)    -[:`_normal`]-> (list)    -[:`_end`]->
  (listE) -[:`_normal`]-> (fdE)
