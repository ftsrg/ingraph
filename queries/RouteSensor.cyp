MATCH (route:Route)-[:gathers]->(sensor:Sensor)
RETURN DISTINCT route, sensor
