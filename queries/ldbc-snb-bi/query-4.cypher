MATCH (:Country {id: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person: Person)<-[:HAS_MODERATOR]-(forum:Forum)-[:IS_CONTAINER_OF]->(post:Post)-[:HAS_TAG]->(:Tag)-[:HAS_TYPE]->(:TagClass {id: $tagClass})
RETURN forum.id, forum.title, forum.creationDate, person.id, count(post) AS count
ORDER BY count DESC, forum.id ASC
LIMIT 20
