MATCH
  (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:requires]->(sensor1:Sensor),
  (sensor1)<-[:monitoredBy]-(te1)-[:connectsTo]->(te2)-[:monitoredBy]->(sensor2:Sensor)<-[:requires]-(route2:Route)
WHERE NOT (semaphore)<-[:entry]-(route2)
  AND route1 <> route2
RETURN semaphore.id, route1.id, route2.id, sensor1.id, sensor2.id, te1, te2
