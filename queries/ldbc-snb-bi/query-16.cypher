MATCH
  (:Country {name: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person:Person)-[:KNOWS*5..8]-(:Person),
  (person)<-[:HAS_CREATOR]-(message:Message)-[:HAS_TAG]->(:Tag)-[:HAS_TYPE]->(:TagClass {name: $tagClass}),
  (message)-[:HAS_TAG]->(tag:Tag)
RETURN person.id, tag.name, count(message) AS messageCount
ORDER BY messageCount DESC, tag.name ASC, person.id ASC
LIMIT 100
