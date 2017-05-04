// Social normals
MATCH
  (country:Country),
  (country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(somePerson:Person),
  (country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(friendOfSomePersion:Person),
  (somePerson)-[:knows]-(friendOfSomePerson)
WITH floor(count(friendOfSomePerson)/count(somePerson)) AS socialNormal
MATCH
  (country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person),
  (country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(friendOfPerson:Person),
  (person)-[:knows]-(friendOfPerson)
WITH socialNormal, person, count(friendOfPerson) AS count
WHERE socialNormal = count
RETURN person.id, count
ORDER BY person.id ASC
LIMIT 100
