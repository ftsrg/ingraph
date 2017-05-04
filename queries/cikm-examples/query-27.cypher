MATCH (person:Person)<-[:hasCreator]-(comment:Comment)-[r:replyOf*]->(message:Message)
RETURN person.firstName, person.lastName, comment.content, message.content
