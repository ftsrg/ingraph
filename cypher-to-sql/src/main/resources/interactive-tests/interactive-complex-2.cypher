MATCH (:Person {id:8796093022220})-[:KNOWS]-(friend:Person)<-[:HAS_CREATOR]-(message:Message)
WHERE message.creationDate <= 20121128000000000
RETURN
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName,
  message.id AS postOrCommentId,
  CASE exists(message.content)
    WHEN true THEN message.content
    ELSE message.imageFile
  END AS postOrCommentContent,
  message.creationDate AS postOrCommentCreationDate
ORDER BY postOrCommentCreationDate DESC, toInteger(postOrCommentId) ASC
LIMIT 20
