// Trending Posts
MATCH
  (message:Message)-[:hasCreator]->(creator:Person),
  (message)<-[:likes]-(fan:Person)
WHERE message.creationDate > "2010-01-01T00:00:00.000+0000"
RETURN message.id, message.creationDate, creator.firstName, creator.lastName, fan.firstName, fan.lastName
ORDER BY message.id ASC, creator.id DESC
LIMIT 100
