LOAD CSV FROM 'file:///7-add-comment.csv' AS line FIELDTERMINATOR '|'
WITH
  toInteger(line[3]) AS commentId,
  line[4] AS creationDate,
  line[5] AS locationIp,
  line[6] AS browserUsed,
  line[7] AS content,
  toInteger(line[8]) AS length,
  toInteger(line[9]) AS personId,
  toInteger(line[10]) AS countryId,
  toInteger(line[11]) AS postId,
  toInteger(line[12]) AS otherCommentId,
  split(line[13], ';') AS tagIds
MATCH
  (person:Person {id: personId}),
  (country:Country {id: countryId}),
  (message:Message {id: -1*postId*otherCommentId}) // sorry for the hack
CREATE
  (comment:Comment:Message {id: commentId, creationDate: creationDate, locationIp: locationIp, browserUsed: browserUsed, content: content, length: length}),
  (comment)-[:isLocatedIn]->(country),
  (post)-[:replyOf]->(message)
// Added 229 labels, created 229 nodes, set 1405 properties, created 458 relationships, statement completed in 349 ms.
WITH comment, tagIds
UNWIND tagIds AS tagId
WITH comment, toInteger(tagId) AS tagId
MATCH (tag:Tag {id: tagId})
CREATE (comment)-[:hasTag]->(tag)
// Added 1 label, created 2 nodes, set 6 properties, created 4 relationships, statement completed in 959 ms.
