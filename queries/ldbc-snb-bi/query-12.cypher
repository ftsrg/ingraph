MATCH
  (message:Message)-[:HAS_CREATOR]->(creator:Person),
  (message)<-[:LIKES]-(fan:Person)
WHERE message.creationDate > $creationDateThreshold
WITH message, creator, count(fan) AS likeCount
WHERE likeCount > $likeThreshold
RETURN message.id, message.creationDate, creator.firstName, creator.lastName, likeCount
ORDER BY likeCount DESC, message.id ASC
LIMIT 100
