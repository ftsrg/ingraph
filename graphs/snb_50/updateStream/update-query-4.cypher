LOAD CSV FROM 'file:///4-add-forum.csv' AS line FIELDTERMINATOR '|'
WITH toInteger(line[3]) AS forumId, line[4] AS title, line[5] AS creationDate, toInteger(line[6]) AS personId, toInteger(line[7]) AS tagId
MATCH (person:Person {id: personId}), (tag:Tag {id: tagId})
CREATE
  (forum:Forum {id: forumId, title: title, creationDate: creationDate}),
  (forum)-[:hasModerator]->(person),
  (forum)-[:hasTag]->(tag)
// Added 22 labels, created 22 nodes, set 66 properties, created 44 relationships, statement completed in 888 ms.
