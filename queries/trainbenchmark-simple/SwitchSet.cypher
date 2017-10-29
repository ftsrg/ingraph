MATCH
  (semaphore:Semaphore)
    <-[e:entry]-(route:Route)
    -[f:follows]->(swP:SwitchPosition)
    -[t:target]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND sw.currentPosition <> swP.position
RETURN semaphore, route, swP, sw, sw.currentPosition AS currentPosition, swP.position AS position
