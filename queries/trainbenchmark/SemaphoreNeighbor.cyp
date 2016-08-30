MATCH (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:gathers]->(sensor1:Sensor)<-[:monitoredBy]-(te1)-[:connectsTo]->(te2)-[:monitoredBy]->(sensor2:Sensor)<-[:gathers]-(route2:Route)
WHERE NOT ((semaphore)<-[:entry]-(route2))
      AND route1 != route2
RETURN DISTINCT semaphore, route1, route2, sensor1, sensor2, te1, te2
