MATCH (:Tag {id: $tag})<-[:HAS_TAG]-(message:Message)-[:HAS_CREATOR]->(person: Person),
  (message)<-[:LIKES]-(fan:Person),
  (message)<-[:REPLY_OF*]-(comment:Comment)
WITH person, count(message) AS postCount, count(comment) AS replyCount, count(fan) AS likeCount
RETURN personId, postCount, replyCount, likeCount, 1*postCount+2*replyCount+10*likeCount AS score
ORDER BY score DESC, personId ASC
LIMIT 100
