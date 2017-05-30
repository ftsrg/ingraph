// Stranger's interaction
MATCH
  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(forum1:Forum),
  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(forum2:Forum),
  (forum1)-[:hasMember]->(person:Person)<-[:hasMember]-(forum2),
  (forum1)-[:hasMember]->(stranger:Person)<-[:hasMember]-(forum2)
WHERE NOT (person)-[:knows]-(stranger)
  AND person.birthday > "1950-01-01T00:00:00.000+0000"
RETURN person.id, stranger.id
ORDER BY person.id, stranger.id
LIMIT 100
