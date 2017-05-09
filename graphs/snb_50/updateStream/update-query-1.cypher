LOAD CSV FROM 'file:///1-add-person.csv' AS line FIELDTERMINATOR '|'
WITH
  toInteger(line[3]) AS personId,
  line[4] AS firstName,
  line[5] AS lastName,
  line[6] AS gender,
  line[7] AS birthDay,
  line[8] AS creationDate,
  line[9] AS locationIp,
  line[10] AS browserUsed,
  toInteger(line[11]) AS cityId,
  split(line[12], ';') AS speaks, // { String }
  split(line[13], ';') AS emails, // { String }
  split(line[14], ';') AS tagIds, // { ID }
  split(line[15], ';') AS universities, // { ID, 32-bit Integer }
  split(line[16], ';') AS companies // { ID, 32-bit Integer }
MATCH (city:City {id: cityId})
CREATE
  (person:Person {id: personId, firstName: firstName, lastName: lastName, gender: gender, birthDay: birthDay, creationDate: creationDate, locationIp: locationIp, browserUsed: browserUsed, speaks: speaks, emails: emails}),
  (person)-[:isLocatedIn]->(city)

WITH person, tagIds, universities, companies
UNWIND companies AS company
WITH person, tagIds, universities, split(company, ',') AS companyEntry
WITH person, tagIds, universities, companyEntry[0] AS companyId, companyEntry[1] AS workFrom
MATCH (company:Company {id: companyId})
CREATE (person)-[:workAt {workFrom: workFrom}]->(company)

WITH person, tagIds, universities
UNWIND universities AS univerity
WITH person, tagIds, split(univerity, ',') AS universityEntry
WITH person, tagIds, universityEntry[0] AS universityId, universityEntry[1] AS classYear
MATCH (university:University {id: universityId})
CREATE (person)-[:studyAt {classYear: classYear}]->(university)

WITH person, tagIds
UNWIND tagIds AS tagId
WITH person, toInteger(tagId) AS tagId
MATCH (tag:Tag {id: tagId})
CREATE (comment)-[:hasTag]->(tag)
