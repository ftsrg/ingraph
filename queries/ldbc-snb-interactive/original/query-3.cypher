// Q3. Friends within 2 steps that recently traveled to countries X and Y. Find top 20 friends and friends of friends of a given Person who have made a post or a comment in the foreign CountryX and CountryY within a specified period of DurationInDays after a startDate. Sorted results descending by total number of posts.
MATCH
  (person:Person {id:{1}})-[:KNOWS*1..2]-(friend:Person)<-[:HAS_CREATOR]-(messageX),
  (messageX)-[:IS_LOCATED_IN]->(countryX:Country)
WHERE not(person=friend)
  AND not((friend)-[:IS_LOCATED_IN]->()-[:IS_PART_OF]->(countryX))
  AND countryX.name={2}
  AND messageX.creationDate>={4}
  AND messageX.creationDate<{5}
WITH friend, count(DISTINCT messageX) AS xCount
MATCH (friend)<-[:HAS_CREATOR]-(messageY)-[:IS_LOCATED_IN]->(countryY:Country)
WHERE countryY.name={3}
  AND not((friend)-[:IS_LOCATED_IN]->()-[:IS_PART_OF]->(countryY))
  AND messageY.creationDate>={4}
  AND messageY.creationDate<{5}
WITH
  friend.id AS friendId,
  friend.firstName AS friendFirstName,
  friend.lastName AS friendLastName,
  xCount,
  count(DISTINCT messageY) AS yCount
RETURN
  friendId,
  friendFirstName,
  friendLastName,
  xCount,
  yCount,
  xCount + yCount AS xyCount
ORDER BY xyCount DESC, friendId ASC
LIMIT {6}
