MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND route.active = true
  AND sw.currentPosition <> swP.position
RETURN semaphore.id, route.id, swP.id, sw.id, sw.currentPosition AS currentPosition, swP.position AS position
