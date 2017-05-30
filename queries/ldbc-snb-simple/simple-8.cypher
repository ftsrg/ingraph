// Top tags for country, age, gender, time
MATCH (country:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person)<-[:hasCreator]-(message:Message)-[:hasTag]->(tag:Tag)
RETURN
  country.name AS countryName,
  message.creationDate AS month,
  person.gender AS gender,
  person.birthday AS birthday,
  tag.name AS tagName,
  count(message) AS messageCount
ORDER BY messageCount DESC, tagName ASC, birthday ASC, gender ASC, month ASC, countryName ASC
