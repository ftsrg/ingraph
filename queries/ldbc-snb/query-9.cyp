MATCH (:Person {id:{1}})-[:KNOWS*1..2]-(friend:Person)<-[:HAS_CREATOR]-(message)
WHERE message.creationDate < {2}
RETURN DISTINCT
message.id AS messageId,
CASE has(message.content)
WHEN true THEN message.content
ELSE message.imageFile
END AS messageContent,
message.creationDate AS messageCreationDate,
friend.id AS personId,
friend.firstName AS personFirstName,
friend.lastName AS personLastName
ORDER BY message.creationDate DESC, message.id ASC
LIMIT {3}
