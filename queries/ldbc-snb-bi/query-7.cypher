MATCH
  (tag:Tag {id: $tag})<-[:HAS_TAG]-(:Message)-[:HAS_CREATOR]->(person1:Person)
MATCH
  (person)<-[:HAS_CREATOR]-(message:Message)-[:HAS_TAG]->(tag),
  (message)<-[:LIKES]-(person2:Person)<-[:HAS_CREATOR]-(:Message)<-[:LIKE]-(person3:Person)
RETURN
  person1.id, count(person3) AS authorityScore
ORDER BY authorityScore DESC, person1.id ASC
LIMIT 100
