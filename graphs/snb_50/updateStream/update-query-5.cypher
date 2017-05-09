LOAD CSV FROM 'file:///5-add-forum-membership.csv' AS line FIELDTERMINATOR '|'
WITH toInteger(line[3]) AS forumId, toInteger(line[4]) AS personId, line[5] AS joinDate
MATCH (person:Person {id: personId}), (forum:Forum {id: forumId})
CREATE (forum)-[:hasMember {joinDate: joinDate}]->(person)
