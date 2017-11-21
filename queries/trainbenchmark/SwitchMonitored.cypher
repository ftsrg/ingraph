MATCH (sw:Switch)
WHERE NOT ((sw)-[:monitoredBy]->(:Sensor))
RETURN sw
