MATCH (comment:Comment)-[r:replyOf*]->(message:Message)
RETURN message, r, comment
