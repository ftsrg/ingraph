MATCH (s:Station)-[:INCL]->(re:Element)
WHERE NOT (re)<-[:ON]-(:Train)
RETURN DISTINCT s
