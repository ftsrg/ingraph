LOAD CSV FROM 'file:///3-add-comment-like.csv' AS line FIELDTERMINATOR '|'
WITH toInteger(line[3]) AS source, toInteger(line[4]) AS target, line[5] AS creationDate
MATCH (person:Person {id: source}), (comment:Comment {id: target})
CREATE (person)-[:likes {creationDate: creationDate}]->(comment)
// Set 5 properties, created 5 relationships, statement completed in 156 ms.
