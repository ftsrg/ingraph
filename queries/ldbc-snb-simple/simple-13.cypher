// Most active Posters of a given Topic
MATCH (:Tag)<-[:hasTag]-(message:Message)-[:hasCreator]->(person: Person),
  (message)<-[:likes]-(fan:Person),
  (message)<-[:replyOf*]-(comment:Comment)
RETURN person.id, message.content, fan.id, comment.id
ORDER BY person.id, message.content, fan.id, comment.id
LIMIT 100
