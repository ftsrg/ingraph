MATCH (person:Person {id:15393162790207})-[:KNOWS*1..2]-(friend:Person)<-[:HAS_CREATOR]-(messageX),
(messageX)-[:IS_LOCATED_IN]->(countryX:Place)
WHERE
  not(person=friend)
  AND not((friend)-[:IS_LOCATED_IN]->()-[:IS_PART_OF]->(countryX))
  AND countryX.name='Puerto_Rico' AND messageX.creationDate>=20101201000000000
  AND messageX.creationDate<20101231000000000
WITH friend, count(DISTINCT messageX) AS xCount
MATCH (friend)<-[:HAS_CREATOR]-(messageY)-[:IS_LOCATED_IN]->(countryY:Place)
WHERE
  countryY.name='Republic_of_Macedonia'
  AND not((friend)-[:IS_LOCATED_IN]->()-[:IS_PART_OF]->(countryY))
  AND messageY.creationDate>=20101201000000000
  AND messageY.creationDate<20101231000000000
WITH
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName,
  xCount,
  count(DISTINCT messageY) AS yCount
RETURN
  personId,
  personFirstName,
  personLastName,
  xCount,
  yCount,
  xCount + yCount AS count
ORDER BY count DESC, toInteger(personId) ASC
LIMIT 20
