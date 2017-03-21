MATCH (homeCountry:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(:Person)<-[:hasCreator]-(message:Message)-[:isLocatedIn]->(country:Country)
WHERE homeCountry <> country
RETURN count(message) AS messageCount, country.name, message.creationDate AS month // TODO extract month
ORDER BY messageCount DESC, country.name ASC, month DESC
LIMIT 100
