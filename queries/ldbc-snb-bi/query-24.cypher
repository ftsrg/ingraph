// Messages by Topic and Continent
MATCH
  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(message:Message)<-[:likes]-(person:Person),
  (message)-[:isLocatedIn]->(:Country)-[:isPartOf]->(continent:Continent)
RETURN
  count(message) AS messageCount,
  count(person) AS likeCount,
  toInt(substring(message.creationDate, 0, 4)) AS year,
  toInt(substring(message.creationDate, 5, 2)) AS month,
  continent.name
