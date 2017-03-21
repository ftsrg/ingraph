MATCH
  (message:Message)-[:hasCreator]->(creator:Person),
  (message)<-[:likes]-(fan:Person)
WHERE true // message.creationDate > $creationDateThreshold
WITH message, creator, count(fan) AS likeCount
WHERE likeCount > 0
RETURN message.id, message.creationDate, creator.firstName, creator.lastName, likeCount
ORDER BY likeCount DESC, message.id ASC
LIMIT 100
