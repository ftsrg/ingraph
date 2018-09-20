MATCH (person:Person {id:30786325579101})-[:KNOWS*1..2]-(friend:Person)
WHERE not(person=friend)
WITH DISTINCT friend
MATCH (friend)-[worksAt:WORK_AT]->(company:Organisation)-[:IS_LOCATED_IN]->(:Place {name:'Puerto_Rico'})
WHERE worksAt.workFrom < 2004
RETURN
  friend.id AS personId,
  friend.firstName AS personFirstName,
  friend.lastName AS personLastName,
  company.name AS organizationName,
  worksAt.workFrom AS organizationWorkFromYear
ORDER BY organizationWorkFromYear ASC, toInteger(personId) ASC, organizationName DESC
LIMIT 10
