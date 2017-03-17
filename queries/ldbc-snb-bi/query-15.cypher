MATCH
  (country:Country {id: $country}),
  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(somePerson:Person),
  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(friendOfSomePersion:Person),
  (somePerson)-[:KNOWS]-(friendOfSomePerson)
WITH floor(count(friendOfSomePerson)/count(somePerson)) AS socialNormal
MATCH
  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person:Person),
  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(friendOfPerson:Person),
  (person)-[:KNOWS]-(friendOfPerson)
WITH socialNormal, person, count(friendOfPerson) AS count
WHERE socialNormal = count
RETURN person.id, count
ORDER BY person.id ASC
LIMIT 100
