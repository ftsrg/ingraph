MATCH
  (vds:VariableDeclarationStatement)-[:declaration]->(vdion:VariableDeclaration)
  -[:declarators]->(vdor:VariableDeclarator)-[:init]->(exp:Expression),

  (vds)     -[:`_end`]->  (vdsE:End),
  (vdion)   -[:`_end`]->  (vdionE:End),
  (exp)     -[:`_end`]->  (expE:End)

MERGE (vdion)   -[:`_normal`]-> (vdionE)

MERGE
  (vds)     -[:`_normal`]-> (exp)   -[:`_end`]->
  (expE)    -[:`_normal`]-> (vdion) -[:`_end`]->
  (vdionE)  -[:`_normal`]-> (vdsE)
