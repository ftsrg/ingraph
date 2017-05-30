MATCH (country:Country)<-[:isLocatedIn]-(message:Message)-[:hasTag]->(tag:Tag)
RETURN country.name, message.content, tag.name
ORDER BY country.name, message.content, tag.name
LIMIT 100
