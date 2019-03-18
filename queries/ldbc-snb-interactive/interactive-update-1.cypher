// Interactive update 1
MATCH (c:City {id:$cityId})
CREATE (p:Person {id: $personId, firstName: $personFirstName, lastName: $personLastName, gender: $gender, birthday: $birthday, creationDate: $creationDate, locationIP: $locationIP, browserUsed: $browserUsed, speaks: $languages, emails: $emails})-[:IS_LOCATED_IN]->(c)
WITH p, count(*) AS dummy1

MATCH (t:Tag)
WHERE t.id IN $tagIds
CREATE (p)-[:HAS_INTEREST]->(t)
WITH p, count(*) AS dummy2

MATCH (u:Organisation)
WHERE u.id IN [ s IN $studyAt | s[0] ]
CREATE (p)-[:STUDY_AT {classYear: [ s IN $studyAt WHERE s[0] = u.id | s[1] ][0] }]->(u)
WITH p, count(*) AS dummy3

MATCH (comp:Organisation)
WHERE comp.id IN [ w IN $workAt | w[0] ]
CREATE (p)-[:WORKS_AT {workFrom: [ w IN $workAt WHERE w[0] = comp.id | w[1] ][0] }]->(comp)
