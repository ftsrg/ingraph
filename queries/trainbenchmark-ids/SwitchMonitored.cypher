MATCH (sw:Switch)
WHERE NOT (sw)-[:monitoredBy]->()
RETURN sw.id
