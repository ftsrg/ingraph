MATCH
  (l:List)-[:`0`]->(first),
  (l)-[:last]->(last),

  (l)     -[:`_end`]->  (lE:End),
  (last)  -[:`_end`]->  (lastE:End)

MERGE (l)     -[:`_normal`]-> (first)
MERGE (lastE) -[:`_normal`]-> (lE)