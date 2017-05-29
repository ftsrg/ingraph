MATCH (:Comment)-[r:replyOf*]->(message:Message)
RETURN message.content
ORDER BY message.content
