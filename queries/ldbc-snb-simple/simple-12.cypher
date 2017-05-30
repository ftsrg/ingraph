// Top posters in a country
MATCH (country:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person)<-[:hasMember]-(forum:Forum)
RETURN country.name, forum.title
ORDER BY country.name, forum.title
LIMIT 100
