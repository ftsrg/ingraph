MATCH
  (bs:BlockStatement)-[:block]->(b:Block)-[:statements]->(list:List),

  (bs)    -[:`_end`]->  (bsE:End),
  (list)  -[:`_end`]->  (listE:End)

MERGE
  (bs)    -[:`_normal`]-> (list)    -[:`_end`]->
  (listE) -[:`_normal`]-> (bsE)
