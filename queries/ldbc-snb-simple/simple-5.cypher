MATCH
  (tag:Tag)<-[:hasTag]-(message:Message)<-[:replyOf*]-(comment:Comment)
RETURN message.content, comment.content, tag.name
ORDER BY message.content, comment.content, tag.name
