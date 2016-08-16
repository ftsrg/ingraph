MATCH (person:Person {id:{1}})-[:KNOWS*1..2]-(friend:Person)
WHERE not(person=friend)
WITH DISTINCT friend
MATCH (friend)-[worksAt:WORKS_AT]->(company:Company)-[:IS_LOCATED_IN]->(:Country {name:{3}})
WHERE worksAt.workFrom < {2}
RETURN
friend.id AS friendId,
friend.firstName AS friendFirstName,
friend.lastName AS friendLastName,
worksAt.workFrom AS workFromYear,
company.name AS companyName
ORDER BY workFromYear ASC, friendId ASC, companyName DESC
LIMIT {4}
