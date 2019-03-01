// Interactive update 4
MATCH (p:Person {id: $moderatorPersonId})
CREATE (f:Forum {id: $forumId, title: $forumTitle, creationDate: $creationDate})-[:HAS_MODERATOR]->(p)
WITH f
MATCH (t:Tag)
WHERE t.id IN $tagIds
CREATE (f)-[:HAS_TAG]->(t)
