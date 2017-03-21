MATCH
  (:TagClass {id: $tagClass1})<-[:hasType]-(:Tag)<-[:hasTag]-(post1:Post)<-[:containerOf]-(forum:Forum)-[:containerOf]->(post2:Post)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass {id: $tagClass2}),
  (forum)-[:hasMember]->(person:Person)
WITH forum, count(post1) AS count1, count(post2) AS count2, count(person) AS members
WHERE members > $threshold
RETURN forum.id, count1, count2
ORDER BY count2 DESC, count1 DESC, forum.id ASC
LIMIT 100
