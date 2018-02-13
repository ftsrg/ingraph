MATCH
  (ltag:Tag)<-[:`_type`]-(left:Expression)
  <-[:left]-(exp:BinaryExpression)-[:right]->
  (right:Expression)-[:`_type`]->(rtag:Tag),

  (ts:TypeSystem)-[:`_instance`]->(btag:Tag:`Boolean`)

WHERE
  exp.operator = 'LogicalOr'

MERGE (exp)-[:`_type`]->(rtag)
MERGE (exp)-[:`_type`]->(ltag)
MERGE (exp)-[:`_type`]->(btag)
