MATCH (person:Person {id:10995116278874})-[:KNOWS]-(:Person)<-[:HAS_CREATOR]-(post:Post)-[:HAS_TAG]->(tag:Tag)
WHERE post.creationDate >= 20120601000000000
   AND post.creationDate < 20120629000000000
WITH person, count(post) AS postsOnTag, tag
OPTIONAL MATCH (person)-[:KNOWS]-()<-[:HAS_CREATOR]-(oldPost:Post)-[:HAS_TAG]->(tag)
WHERE oldPost.creationDate < 20120601000000000
WITH person, postsOnTag, tag, count(oldPost) AS cp
WHERE cp = 0
RETURN
  tag.name AS tagName,
  sum(postsOnTag) AS postCount
ORDER BY postCount DESC, tagName ASC
LIMIT 10
