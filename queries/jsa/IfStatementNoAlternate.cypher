MATCH
  (test:Node)<-[:test]-(if:IfStatement)-[:consequent]->(consequent:Node),

  (if)          -[:`_end`]->  (ifE:End),
  (test)        -[:`_end`]->  (testE:End),
  (consequent)  -[:`_end`]->  (consequentE:End)

WHERE
  NOT (if)-[:alternate]->(:Statement)

MERGE
  (if)			    -[:`_normal`]-> (test)         -[:`_end`]->
	(testE)   		-[:`_true`]->   (consequent)   -[:`_end`]->
	(consequentE)	-[:`_normal`]-> (ifE)
