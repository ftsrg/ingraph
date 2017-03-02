// Q5. New groups. Given a start Person, find the top 20 Forums the friends and friends of friends of that Person joined after a given Date. Sort results descending by the number of Posts in each Forum that were created by any of these Persons.
MATCH (person:Person)-[:KNOWS*1..2]-(friend:Person)<-[membership:HAS_MEMBER]-(forum:Forum)
WHERE membership.joinDate > $date
  AND NOT(person = friend) // I think this condition is unnecessary as Cypher will not travel the same edge twice (szarnyasg)
WITH DISTINCT friend, forum
OPTIONAL MATCH (friend)<-[:HAS_CREATOR]-(post:Post)<-[:CONTAINER_OF]-(forum)
WITH forum, count(post) AS postCount
RETURN
  forum.title AS forumName,
  postCount
ORDER BY
  postCount DESC,
  forum.id ASC
LIMIT 10
