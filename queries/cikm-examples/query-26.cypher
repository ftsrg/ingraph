MATCH (comment:Comment)-[r:replyOf*]->(message:Message)
RETURN message.content, comment.content
