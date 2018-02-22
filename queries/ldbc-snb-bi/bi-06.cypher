MATCH (message:Message)-[:HAS_CREATOR]->(person:Person)
WITH
  person,
  count(message) AS likeCount
RETURN
  person.id,
  likeCount
