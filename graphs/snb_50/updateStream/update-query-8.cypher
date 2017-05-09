LOAD CSV FROM 'file:///8-add-friendship.csv' AS line FIELDTERMINATOR '|'
WITH toInteger(line[3]) AS source, toInteger(line[4]) AS target, line[5] AS creationDate
MATCH (p1:Person {id: source}), (p2:Person {id: target})
CREATE (p1)-[:KNOWS {creationDate: creationDate}]->(p2)
// Set 8 properties, created 8 relationships, statement completed in 119 ms.
