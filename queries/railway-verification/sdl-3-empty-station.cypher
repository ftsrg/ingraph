MATCH (s:Station)-[:INCLUDES]->(re:RailroadElement)
WHERE NOT (re)<-[:ON]-(:Train)
RETURN s
