Distinct([sw]) # distinct can be ignored by a smart optimizer
 |
AntiJoin([sw]) # antijoin on the listed variables that appear on both sides
 | |
 | +----------------------------------------------------+
 |                                                      |
 |                                                     ExpandOut(edgeVar=:monitoredBy, vertexVar=:Sensor)
 |                                                      |
GetVertices(vertexVar=sw:Switch)                       GetVertices(vertexVar=sw:Switch)
