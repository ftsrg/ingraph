MATCH (country:Country {name: $country})
WITH
  country,
  $endDate/10000000000000   AS endDateYear,
  $endDate/100000000000%100 AS endDateMonth
MATCH
  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-
  (person:Person)<-[:HAS_CREATOR]-(message:Message)
WHERE person.creationDate < $endDate
  AND message.creationDate < $endDate
WITH
  country,
  person,
  endDateYear,
  endDateMonth,
  message.creationDate/10000000000000   AS personCreationYear,
  message.creationDate/100000000000%100 AS personCreationMonth,
  count(message) AS messageCount
WITH
  country,
  person,
  messageCount,
  (endDateYear  - personCreationYear ) * 12 +
  (endDateMonth - personCreationMonth) AS months
WITH
  country,
  person,
  messageCount,
  CASE WHEN months = 0 THEN 0 ELSE messageCount / months END AS creationScore
WHERE creationScore < 1
WITH
  country,
  collect(person) AS zombies
MATCH
  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person:Person)
OPTIONAL MATCH
  (person:Person)<-[:HAS_CREATOR]-(message:Message)<-[:LIKES]-(fan:Person)
WHERE fan.creationDate < $endDate
WITH
  zombies,
  person,
  collect(fan) AS fans
WITH
  person,
  size([f IN fans WHERE f in zombies]) AS zombieLikeCount,
  toFloat(size(fans)) AS totalLikeCount
RETURN
  person.id,
  zombieLikeCount,
  totalLikeCount,
  CASE WHEN totalLikeCount = 0 THEN 0 ELSE zombieLikeCount / totalLikeCount END AS zombieScore
ORDER BY
  zombieScore DESC,
  person.ID ASC
LIMIT 100