MATCH
  (es:ExpressionStatement)-[:expression]->(exp:Expression),

  (es)  -[:`_end`]->  (esE:End),
  (exp) -[:`_end`]->  (expE:End)

MERGE
  (es)    -[:`_normal`]-> (exp)   -[:`_end`]->
  (expE)  -[:`_normal`]-> (esE)
