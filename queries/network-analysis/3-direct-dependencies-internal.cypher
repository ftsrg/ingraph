MATCH (website)-[:DEPENDS_ON]->(downstream)
WHERE website.system = "INTRANET"
RETURN website.host as Host, collect(downstream.host) as Dependencies
ORDER BY Host
