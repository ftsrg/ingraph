MATCH (homeCountry:Country {name: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(:Person)<-[:HAS_CREATOR]-(message:Message)-[:IS_LOCATED_IN]->(:City)-[:IS_PART_OF]->(country:Country)
WHERE homeCountry <> country
RETURN count(message) AS messageCount, country.name, message.creationDate AS month // TODO extract month
ORDER BY messageCount DESC, country.name ASC, month DESC
LIMIT 100
