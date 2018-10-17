MATCH (person:Person {id:15393162790207})-[:KNOWS*1..2]-(friend:Person)<-[membership:HAS_MEMBER]-(forum:Forum)
WHERE membership.joinDate>20120811000000000
    AND not(person=friend)
WITH DISTINCT friend, forum
OPTIONAL MATCH (friend)<-[:HAS_CREATOR]-(post:Post)<-[:CONTAINER_OF]-(forum)
WITH forum, count(post) AS postCount
RETURN
  forum.title AS forumTitle,
  postCount
ORDER BY postCount DESC, toInteger(forum.id) ASC
LIMIT 20
