MATCH
  (v:Variable)-[:references]->(r:Reference)-[:node]->(bid:BindingIdentifier),
  (bid)-[:`_type`]->(tag:Tag)

MERGE
  (v)-[:`_type`]->(tag)
