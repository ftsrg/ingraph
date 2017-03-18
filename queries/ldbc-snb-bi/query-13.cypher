MATCH (:Country {name: $country})<-[:IS_LOCATED_IN]-(message:Message)
WITH message.creationDate AS year, message.creationDate AS month
MATCH (message:Message)-[:HAS_TAG]->(tag:Tag)
WHERE month < message.creationDate < year // TODO check properly
WITH year, month, tag, count(message) AS popularity
ORDER BY popularity DESC, tag.name DESC
RETURN year, month, collect([tag.name, popularity]) AS popularTags
ORDER BY year DESC, month ASC
LIMIT 100
