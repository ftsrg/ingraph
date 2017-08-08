MATCH (comment:Comment)-[:replyOf*]->(message:Message)
RETURN comment.content, message.content
ORDER BY comment.content, message.content
