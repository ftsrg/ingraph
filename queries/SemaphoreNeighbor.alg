Distinct([semaphore, route1, route2, sensor1, sensor2, te1, te2]) # distinct can't be ignored as edges can be multiplied
 |
Filter(route1 != route2)
 |
AntiJoin([route, sensor]) # antijoin on the listed variables that appear on both sides
 | |
 | +----------------------------------------------------------+
 |                                                            |
AllDifferent([_e1, _e2, _e3, _e4, _e5, _e6])                  |
 |                                                            |
ExpandIn(edgeVar=_e6:gathers, vertexVar=route2:Route)         |
 |                                                            |
ExpandOut(edgeVar=_e5:monitoredBy, vertexVar=sensor2:Sensor)  |
 |                                                            |
ExpandOut(edgeVar=_e4:connectsTo, vertexVar=te2)              |
 |                                                            |
ExpandIn(edgeVar=_e3:monitoredBy, vertexVar=te1)              |
 |                                                            |
ExpandOut(edgeVar=_e2:gathers, vertexVar=sensor1:Sensor)      |
 |                                                            |
ExpandIn(edgeVar=_e1:exit, vertexVar=route1:Route)           ExpandIn(edgeVar=:entry, vertexVar=route2:Route)
 |                                                            |
GetVertices(vertexVar=semaphore:Semaphore)                   GetVertices(vertexVar=semaphore:Semaphore)
