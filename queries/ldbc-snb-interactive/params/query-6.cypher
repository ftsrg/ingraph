// Q6. Tag co-occurrence. Given a start Person and some Tag, find the other Tags that occur together with this Tag on Posts that were created by Person's friends and friends of friends. Return top 10 Tags, sorted descending by the count of Posts that were created by these Persons, which contain both this Tag and the given Tag.
MATCH
  (person:Person)-[:knows*1..2]-(friend:Person),
  (friend)<-[:hasCreator]-(friendPost:Post)-[:hasTag]->(knownTag:Tag)
WHERE NOT(person = friend) // I think this condition is unnecessary as Cypher will not travel the same edge twice (szarnyasg)
MATCH (friendPost)-[:hasTag]->(commonTag:Tag)
WHERE NOT(commonTag = knownTag)
WITH DISTINCT commonTag, knownTag, friend
MATCH (commonTag)<-[:hasTag]-(commonPost:Post)-[:hasTag]->(knownTag)
WHERE (commonPost)-[:hasCreator]->(friend)
RETURN
  commonTag.name AS tagName,
  count(commonPost) AS postCount
ORDER BY
  postCount DESC,
  tagName ASC
LIMIT 10
