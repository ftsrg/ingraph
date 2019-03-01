MATCH
  (l:List),

  (l)     -[:`_end`]->  (lE:End)

WHERE
  NOT (l)-[:`0`]->()

MERGE
  (l)     -[:`_normal`]-> (lE)
