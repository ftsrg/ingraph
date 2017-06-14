MATCH (n)<-[:DEPENDS_ON*]-(dependent)
RETURN n.host as Host, count(DISTINCT dependent) AS Dependents
ORDER BY Dependents DESC
LIMIT 1
