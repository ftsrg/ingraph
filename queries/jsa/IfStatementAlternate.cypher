MATCH
  (test:Node)<-[:test]-(if:IfStatement)-[:consequent]->(consequent:Node),
  (if)-[:alternate]->(alternate:Statement),

  (alternate)   -[:`_end`]->    (alternateE:End),

  (if)          -[:`_end`]->  (ifE:End),
  (test)        -[:`_end`]->  (testE:End),
  (consequent)  -[:`_end`]->  (consequentE:End)

MERGE
  (if)          -[:`_normal`]-> (test)        -[:`_end`]->
  (testE)       -[:`_true`]->   (consequent)  -[:`_end`]->
  (consequentE) -[:`_normal`]-> (ifE)

MERGE
  (testE)       -[:`_false`]->  (alternate)   -[:`_end`]->
  (alternateE)  -[:`_normal`]-> (ifE)
