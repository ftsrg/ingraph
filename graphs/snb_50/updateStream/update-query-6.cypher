LOAD CSV FROM 'file:///6-add-post.csv' AS line FIELDTERMINATOR '|'
WITH
  toInteger(line[3]) AS postId,
  line[4] AS imageFile,
  line[5] AS creationDate,
  line[6] AS locationIp,
  line[7] AS browserUsed,
  line[8] AS language,
  line[9] AS content,
  toInteger(line[10]) AS length,
  toInteger(line[11]) AS personId,
  toInteger(line[12]) AS forumId,
  toInteger(line[13]) AS countryId,
  split(line[14], ';') AS tagIds
MATCH (person:Person {id: personId}), (forum:Forum {id: forumId}), (country:Country {id: countryId})
CREATE
  (post:Post:Message {id: postId, imageFile: imageFile, creationDate: creationDate, locationIp: locationIp, browserUsed: browserUsed, language: language, content: content, length: length}),
  (forum)-[:containerOf]->(post),
  (post)-[:isLocatedIn]->(country)
WITH post, tagIds
UNWIND tagIds AS tagId
WITH post, toInteger(tagId) AS tagId
MATCH (tag:Tag {id: tagId})
CREATE (post)-[:hasTag]->(tag)
// Added 229 labels, created 229 nodes, set 1405 properties, created 458 relationships, statement completed in 9937 ms.
