// Q9. Latest Posts. Find the most recent 20 posts and comments from all friends, or friends-of-friends of Person, but created before a Date. Return posts, their creators and creation dates, sort descending by creation date.
MATCH (:Person)-[:knows*1..2]-(friend:Person)<-[:hasCreator]-(message)
WHERE message.creationDate < "2050-01-01T00:00:00.000+0000"
RETURN DISTINCT
  message.id AS messageId,
  coalesce(message.content, message.imageFile) AS messageContent,
  message.creationDate AS messageCreationDate,
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName
ORDER BY message.creationDate DESC, message.id ASC
LIMIT 10
