// Q11. Job referral. Find top 10 friends of the specified Person, or a friend of her friend (excluding the specified person), who has long worked in a company in a specified Country. Sort ascending by start date, and then ascending by person identifier.
MATCH (person:Person)-[:knows*1..2]-(friend:Person)
WHERE NOT(person = friend) // I think this condition is unnecessary as Cypher will not travel the same edge twice (szarnyasg)
WITH DISTINCT friend
MATCH (friend)-[worksAt:worksAt]->(company:Company)-[:isLocatedIn]->(:Country)
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
