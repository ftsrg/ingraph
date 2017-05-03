MATCH (p1:Person)-[:knows]->(p2:Person),
  //(p1)-[:studyAt]->(:University)<-[:studyAt]-(p2),
  (p1)<-[:hasCreator]-(m:Message:Post)<-[:likes]-(p2)
WITH p1, p2, count(m) AS likedMessageCount
WHERE likedMessageCount = 1
RETURN p1, p2
