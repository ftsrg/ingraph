MATCH
  (semaphore:Semaphore)
    <-[e:exit]-(route1:Route)
    -[r1:requires]->(sensor1:Sensor)
    <-[m1:monitoredBy]-(te1)
    -[ct:connectsTo]->(te2)
    -[m2:monitoredBy]->(sensor2:Sensor)
    <-[r2:requires]-(route2:Route)
WHERE NOT (semaphore)<-[:entry]-(route2)
      AND route1 <> route2
RETURN semaphore, route1, route2, sensor1, sensor2, te1, te2
