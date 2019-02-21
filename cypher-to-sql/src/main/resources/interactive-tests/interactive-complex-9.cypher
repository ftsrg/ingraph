MATCH (:Person {id:32985348834013})-[:KNOWS*1..2]-(friend:Person)<-[:HAS_CREATOR]-(message)
WHERE message.creationDate < 20120828000000000
RETURN DISTINCT
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName,
  message.id AS commentOrPostId,
  CASE exists(message.content)
    WHEN true THEN message.content
    ELSE message.imageFile
  END AS commentOrPostContent,
  message.creationDate AS commentOrPostCreationDate
ORDER BY message.creationDate DESC, toInteger(message.id) ASC
LIMIT 20

