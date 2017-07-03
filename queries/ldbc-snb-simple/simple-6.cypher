MATCH
  (comment:Comment)-[:replyOf*]->(message:Message)-[:hasTag]->(tag:Tag)
RETURN message.content, comment.content, tag.name
ORDER BY message.content, comment.content, tag.name
