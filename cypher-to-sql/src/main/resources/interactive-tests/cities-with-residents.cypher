// place should be city, since Persons live in cities
MATCH (:Person)-[:IS_LOCATED_IN]->(place)
RETURN place.id
ORDER BY place.id
