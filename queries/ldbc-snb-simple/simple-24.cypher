// Messages by Topic and Continent
MATCH
  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(message:Message)<-[:likes]-(person:Person),
  (message)-[:isLocatedIn]->(:Country)-[:isPartOf]->(continent:Continent)
WITH message, person, continent
RETURN count(message) AS messageCount, count(person) AS likeCount, continent.name
ORDER BY messageCount, likeCount, continent.name
LIMIT 100
