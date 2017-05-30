MATCH (person:Person)<-[:hasCreator]-(message:Message)<-[:replyOf*]-(reply:Message)
WHERE message.creationDate >= "2010-01-01T00:00:00.000+0000"
  AND message.creationDate <= "2011-01-01T00:00:00.000+0000"
  AND reply.creationDate   >= "2010-01-01T00:00:00.000+0000"
  AND reply.creationDate   <= "2011-01-01T00:00:00.000+0000"
RETURN person.id, person.firstName, person.lastName, person.creationDate, message.creationDate
ORDER BY person.id, person.firstName, person.lastName, person.creationDate, message.creationDate
LIMIT 100
