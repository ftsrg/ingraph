// Most authoritative users on a given topic
MATCH
  (tag:Tag)<-[:hasTag]-(:Message)-[:hasCreator]->(person1:Person)
MATCH
  (person)<-[:hasCreator]-(message:Message)-[:hasTag]->(tag),
  (message)<-[:likes]-(person2:Person)<-[:hasCreator]-(:Message)<-[:likes]-(person3:Person)
RETURN person1.id, person2.id, person3.id, tag.name
ORDER BY person1.id, person2.id, person3.id, tag.name
LIMIT 100
