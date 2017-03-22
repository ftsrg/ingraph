// Q1. Extract description of friends with a given name Given a person's firstName, return up to 20 people with the same first name, sorted by increasing distance (max 3) from a given person, and for people within the same distance sorted by last name. Results should include the list of workplaces and places of study.
MATCH (:Person {id: $id})-[path:KNOWS*1..3]-(friend:Person)
WHERE friend.firstName = $firstName
WITH friend, min(length(path)) AS distance
ORDER BY distance ASC, friend.lastName ASC, friend.id ASC
LIMIT 10

MATCH (friend)-[:IS_LOCATED_IN]->(friendCity:City)
OPTIONAL MATCH (friend)-[studyAt:STUDY_AT]->(uni:University)-[:IS_LOCATED_IN]->(uniCity:City)
WITH friend,
  collect([uni.name, studyAt.classYear, uniCity.name]) AS unis,
  friendCity,
  distance
OPTIONAL MATCH (friend)-[worksAt:WORKS_AT]->(company:Company)-[:IS_LOCATED_IN]->(companyCountry:Country)
WITH friend,
  collect([company.name, worksAt.workFrom, companyCountry.name]) AS companies,
  unis,
  friendCity,
  distance
RETURN
  friend.id AS id,
  friend.lastName AS lastName,
  distance,
  friend.birthday AS birthday,
  friend.creationDate AS creationDate,
  friend.gender AS gender,
  friend.browserUsed AS browser,
  friend.locationIP AS locationIp,
  friend.email AS emails,
  friend.languages AS languages,
  friendCity.name AS cityName,
  unis,
  companies
ORDER BY distance ASC, friend.lastName ASC, friend.id ASC
LIMIT 10
