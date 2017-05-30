MATCH (message:Message)<-[r:replyOf*]-(comment:Comment)
RETURN message.content, comment.content
ORDER BY message.content, comment.content
