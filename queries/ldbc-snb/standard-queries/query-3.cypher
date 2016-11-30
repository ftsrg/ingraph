MATCH
  (person:Person)-[:KNOWS*1..2]-(friend:Person)<-[:HAS_CREATOR]-(messageX),
  (messageX)-[:IS_LOCATED_IN]->(countryX:Country)
WHERE NOT(person = friend) // I think this condition is unnecessary as Cypher will not travel the same edge twice (szarnyasg)
  AND NOT((friend)-[:IS_LOCATED_IN]->()-[:IS_PART_OF]->(countryX))
  AND countryX.name = $countryXName
  AND messageX.creationDate >= $date1
  AND messageX.creationDate < $date2
WITH friend, count(DISTINCT messageX) AS xCount
MATCH (friend)<-[:HAS_CREATOR]-(messageY)-[:IS_LOCATED_IN]->(countryY:Country)
WHERE countryY.name = $countryYName
  AND NOT((friend)-[:IS_LOCATED_IN]->()-[:IS_PART_OF]->(countryY))
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
