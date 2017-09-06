MATCH
  (semaphore:Semaphore)
    <-[e:exit]-(route1:Route)
    -[r1:requires]->(sensor1:Sensor)
    <-[m1:monitoredBy]-(te1)
    -[ct:connectsTo]->(te2)
    -[m2:monitoredBy]->(sensor2:Sensor)
    <-[r2:requires]-(route2:Route)
WHERE NOT ((semaphore)<-[:entry]-(route2))
      AND route1.id <> route2.id
RETURN DISTINCT semaphore.id, route1.id, route2.id, sensor1.id, sensor2.id, te1.id, te2.id
