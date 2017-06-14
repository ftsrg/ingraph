MATCH (website)-[:DEPENDS_ON]->(downstream)
WHERE website.system = 'INTRANET'
RETURN website.host AS Host, collect(downstream.host) AS Dependencies
ORDER BY Host
