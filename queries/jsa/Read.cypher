MATCH
  (v:Variable)-[:references]->(r:Reference)-[:node]->(ide:IdentifierExpression),
  (v)-[:`_type`]->(tag:Tag)

MERGE
  (ide)-[:`_type`]->(tag)
