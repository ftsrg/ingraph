MATCH (:Country {name: $country})<-[:isLocatedIn]-(message:Message)
WITH message.creationDate AS year, message.creationDate AS month
MATCH (message:Message)-[:hasTag]->(tag:Tag)
WHERE month < message.creationDate
  AND message.creationDate < year // TODO check properly
WITH year, month, tag, count(message) AS popularity
ORDER BY popularity DESC, tag.name DESC
RETURN year, month, collect([tag.name, popularity]) AS popularTags
ORDER BY year DESC, month ASC
LIMIT 100
