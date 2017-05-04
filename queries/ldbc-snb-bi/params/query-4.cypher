MATCH (:Country {id: $country})<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person: Person)<-[:hasModerator]-(forum:Forum)-[:is_containerOf]->(post:Post)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass {id: $tagClass})
RETURN forum.id, forum.title, forum.creationDate, person.id, count(post) AS count
ORDER BY count DESC, forum.id ASC
LIMIT 20
