MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND sw.currentPosition <> swP.position
RETURN DISTINCT semaphore, route, swP, sw, sw.currentPosition AS currentPosition, swP.position AS position
