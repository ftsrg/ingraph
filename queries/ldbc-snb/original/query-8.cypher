// Q8. Most recent replies. This query retrieves the 20 most recent reply comments to all the posts and comments of Person, ordered descending by creation date.
MATCH (start:Person {id:{1}})<-[:HAS_CREATOR]-()<-[:REPLY_OF]-(comment:Comment)-[:HAS_CREATOR]->(person:Person)
RETURN
  person.id AS personId,
  person.firstName AS personFirstName,
  person.lastName AS personLastName,
  comment.id AS commentId,
  comment.creationDate AS commentCreationDate,
  comment.content AS commentContent
ORDER BY commentCreationDate DESC, commentId ASC
LIMIT {2}
