MATCH
  (:TagClass {id: $tagClass1})<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-(post1:Post)<-[:CONTAINER_OF]-(forum:Forum)-[:CONTAINER_OF]->(post2:Post)-[:HAS_TAG]->(:Tag)-[:HAS_TYPE]->(:TagClass {id: $tagClass2}),
  (forum)-[:HAS_MEMBER]->(person:Person)
WITH forum, count(post1) AS count1, count(post2) AS count2, count(person) AS members
WHERE members > $threshold
RETURN forum.id, count1, count2
ORDER BY count2 DESC, count1 DESC, forum.id ASC
LIMIT 100
