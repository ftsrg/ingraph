LOAD CSV FROM 'file:///2-add-post-like.csv' AS line FIELDTERMINATOR '|'
WITH toInteger(line[3]) AS source, toInteger(line[4]) AS target, line[5] AS creationDate
MATCH (person:Person {id: source}), (post:Post {id: target})
CREATE (person)-[:likes {creationDate: creationDate}]->(post)
// Set 52 properties, created 52 relationships, statement completed in 463 ms.
