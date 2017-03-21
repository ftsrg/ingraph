MATCH
  (:Country {name: $country})<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person)-[:knows*5..8]-(:Person),
  (person)<-[:hasCreator]-(message:Message)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass {name: $tagClass}),
  (message)-[:hasTag]->(tag:Tag)
RETURN person.id, tag.name, count(message) AS messageCount
ORDER BY messageCount DESC, tag.name ASC, person.id ASC
LIMIT 100
