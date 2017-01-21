Distinct([route, sensor, swP, sw]) # distinct can't be ignored as edges can be multiplied
 |
AntiJoin([route, sensor]) # antijoin on the listed variables that appear on both sides
 | |
 | +----------------------------------------------------------+
 |                                                            |
AllDifferent([_e1, _e2, _e3])                                 |
 |                                                            |
ExpandOut(edgeVar=_e3:monitoredBy, vertexVar=sensor:Sensor)   |
 |                                                            |
ExpandOut(edgeVar=_e2:target, vertexVar=sw:Switch)            |
 |                                                            |
ExpandOut(edgeVar=_e1:follows, vertexVar=swP:SwitchPosition) ExpandOut(edgeVar=:requires, vertexVar=sensor:Sensor)
 |                                                            |
GetVertices(vertexVar=route:Route)                           GetVertices(vertexVar=route:Route)
