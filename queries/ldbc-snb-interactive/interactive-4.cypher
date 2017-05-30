// Q4. New Topics. Given a start Person, find the top 10 most popular Tags (by total number of posts with the tag) that are attached to Posts that were created by that Person's friends within a given time interval.
MATCH (person:Person)-[:knows]-(:Person)<-[:hasCreator]-(post:Post)-[hasTag]->(tag:Tag)
WHERE post.creationDate >= "1950-01-01T00:00:00.000+0000"
  AND post.creationDate < "2050-01-01T00:00:00.000+0000"
OPTIONAL MATCH (tag)<-[:hasTag]-(oldPost:Post)
WHERE oldPost.creationDate < "1950-01-01T00:00:00.000+0000"
WITH tag, post, length(collect(oldPost)) AS oldPostCount
WHERE oldPostCount = 0
RETURN
  tag.name AS tagName,
  length(collect(post)) AS postCount
ORDER BY
  postCount DESC,
  tagName ASC
LIMIT 10
