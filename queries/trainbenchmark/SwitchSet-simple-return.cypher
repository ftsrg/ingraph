MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND sw.currentPosition <> swP.position
RETURN semaphore, route, swP, sw
