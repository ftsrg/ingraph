MATCH (person:Person)-[created:created]->(comment:Comment)-[r:replyOf*]->(message:Message)
RETURN person, created, message, r, comment
