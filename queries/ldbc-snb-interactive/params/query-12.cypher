// Q12. Expert Search. Find friends of a Person who have replied the most to posts with a tag in a given TagCategory. Return top 20 persons, sorted descending by number of replies.
MATCH (:Person)-[:knows]-(friend:Person)
OPTIONAL MATCH
  (friend)<-[:hasCreator]-(comment:Comment)-[:replyOf]->(:Post)-[:hasTag]->(tag:Tag),
  (tag)-[:hasType]->(tagClass:TagClass)-[:isSubclassOf*0..]->(baseTagClass:TagClass)
WHERE baseTagClass.name = $class // this also includes tagClass due to the 0 lower limit
RETURN
  friend.id AS friendId,
  friend.firstName AS friendFirstName,
  friend.lastName AS friendLastName,
  collect(DISTINCT tag.name) AS tagNames,
  count(DISTINCT comment) AS count
ORDER BY
  count DESC,
  friendId ASC
LIMIT 10
