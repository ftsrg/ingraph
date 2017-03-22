// Q3. Friends within 2 steps that recently traveled to countries X and Y. Find top 20 friends and friends of friends of a given Person who have made a post or a comment in the foreign CountryX and CountryY within a specified period of DurationInDays after a startDate. Sorted results descending by total number of posts.
MATCH
  (person:Person)-[:knows*1..2]-(friend:Person)<-[:hasCreator]-(messageX),
  (messageX)-[:isLocatedIn]->(countryX:Country)
WHERE NOT(person = friend) // I think this condition is unnecessary as Cypher will not travel the same edge twice (szarnyasg)
  AND NOT((friend)-[:isLocatedIn]->()-[:isPartOf]->(countryX))
  AND countryX.name = $countryXName
  AND messageX.creationDate >= $date1
  AND messageX.creationDate < $date2
WITH friend, count(DISTINCT messageX) AS xCount
MATCH (friend)<-[:hasCreator]-(messageY)-[:isLocatedIn]->(countryY:Country)
WHERE countryY.name = $countryYName
  AND NOT((friend)-[:isLocatedIn]->()-[:isPartOf]->(countryY))
  AND messageY.creationDate >= $date1
  AND messageY.creationDate < $date2
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
ORDER BY
  xyCount DESC,
  friendId ASC
LIMIT 10
