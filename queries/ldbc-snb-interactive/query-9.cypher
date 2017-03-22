// Q9. Latest Posts. Find the most recent 20 posts and comments from all friends, or friends-of-friends of Person, but created before a Date. Return posts, their creators and creation dates, sort descending by creation date.
MATCH (:Person {id: $person)-[:knows*1..2]-(friend:Person)<-[:hasCreator]-(message)
WHERE message.creationDate < $creationDate
RETURN DISTINCT
  message.id AS messageId,
  coalesce(message.content, message.imageFile) AS messageContent
  message.creationDate AS messageCreationDate,
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName
ORDER BY message.creationDate DESC, message.id ASC
LIMIT 10
