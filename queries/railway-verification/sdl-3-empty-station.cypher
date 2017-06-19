MATCH (s:Station)-[:INCLUDES]->(re:Element)
WHERE NOT (re)<-[:ON]-(:Train)
RETURN DISTINCT s
