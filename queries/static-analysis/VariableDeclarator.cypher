MATCH
  (bi:BindingIdentifier)<-[:binding]-
    (vd:VariableDeclarator)
      -[:init]->(exp:Expression),

  (exp)-[:`_type`]->(tag:Tag)

MERGE
  (bi)-[:`_type`]->(tag)
