// Forum with related Tags
MATCH
  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(post1:Post)<-[:containerOf]-(forum:Forum)-[:containerOf]->(post2:Post)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass),
  (forum)-[:hasMember]->(person:Person)
RETURN forum.id, count(post1) AS count1, count(post2) AS count2
ORDER BY forum.id
LIMIT 100
