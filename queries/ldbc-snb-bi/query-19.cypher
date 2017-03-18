MATCH
  (:TagClass {id: $tagClass1})<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-(forum1:Forum),
  (:TagClass {id: $tagClass2})<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-(forum2:Forum),
  (forum1)-[:HAS_MEMBER]->(person:Person)<-[:HAS_MEMBER]-(forum2),
  (forum1)-[:HAS_MEMBER]->(stranger:Person)<-[:HAS_MEMBER]-(forum2)
WHERE NOT (person)-[:KNOWS]-(stranger)
  AND person.birthday > $date
WITH person, stranger
MATCH (person)<-[:HAS_CREATOR]-(:Message)-[:REPLY_TO]-(comment1:Comment)-[:HAS_CREATOR]->(stranger),
  (stranger)<-[:HAS_CREATOR]-(:Message)<-[:REPLY_TO]-(comment2:Comment)-[:HAS_CREATOR]->(person)
RETURN person.id, count(stranger) AS strangersCount, count(comment1) + count(comment2) AS interactionCount
ORDER BY interactionCount DESC, person.id ASC
LIMIT 100
