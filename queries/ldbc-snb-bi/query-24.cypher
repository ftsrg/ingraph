MATCH
  (:TagClass)<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-(message:Message)<-[:LIKES]-(person:Person),
  (message)-[:IS_LOCATED]->(:Country)-[:IS_PART_OF]->(continent:Continent)
RETURN count(message) AS messageCount, count(person) AS likeCount, message.creationDate AS year, message.creationDate AS month, continent.name
