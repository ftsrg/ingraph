MATCH (:Country {id: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person:Person)<-[:HAS_MEMBER]-(forum:Forum)
WITH forum, count(person) AS numberOfMembers
ORDER BY numberOfMembers DESC
LIMIT 100
MATCH (forum)-[:HAS_MEMBER]->(person:Person)<-[:HAS_CREATOR]-(post:Post)
RETURN person.id, person.firstName, person.lastName, person.creationDate, count(post) AS postCount
ORDER BY postCount DESC, person.id ASC
LIMIT 100
