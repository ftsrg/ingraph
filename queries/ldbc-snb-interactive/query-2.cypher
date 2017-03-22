// Q2. Find the newest 20 posts and comments from your friends. Given a start Person, find (most recent) Posts and Comments from all of that Person's friends, that were created before (and including) a given Date. Return the top 20 Posts/Comments, and the Person that created each of them. Sort results descending by creation date, and then ascending by Post identifier.
MATCH (:Person {id: $person}})-[:knows]-(friend:Person)<-[:hasCreator]-(message)
WHERE message.creationDate <= $creationDate AND (message:Post OR message:Comment)
RETURN
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName,
  message.id AS messageId,
  coalesce(message.content, message.imageFile) AS messageContent
  message.creationDate AS messageDate
ORDER BY messageDate DESC, messageId ASC
LIMIT 10
