MATCH (person:Person)-[:KNOWS*1..2]-(friend:Person)
WHERE NOT(person = friend) // I think this condition is unnecessary as Cypher will not travel the same edge twice (szarnyasg)
WITH DISTINCT friend
MATCH (friend)-[worksAt:WORKS_AT]->(company:Company)-[:IS_LOCATED_IN]->(:Country)
WHERE worksAt.workFrom < $date
RETURN
  friend.id AS friendId,
  friend.firstName AS friendFirstName,
  friend.lastName AS friendLastName,
  worksAt.workFrom AS workFromYear,
  company.name AS companyName
ORDER BY
  workFromYear ASC,
  friendId ASC,
  companyName DESC
LIMIT 10
