Distinct([route, sensor, swP, sw]) # distinct can't be ignored as edges can be multiplied
 |
AntiJoin([route, sensor]) # antijoin on the listed variables that appear on both sides
 | |
 | +--------------------------------------------------------+
 |                                                          |
AllDifferent([_e1, _e2, _e3])                               |
 |                                                          |
ExpandOut(edgeVar=_e3:monitoredBy, nodeVar=sensor:Sensor)   |
 |                                                          |
ExpandOut(edgeVar=_e2:target, nodeVar=sw:Switch)            |
 |                                                          |
ExpandOut(edgeVar=_e1:follows, nodeVar=swP:SwitchPosition) ExpandOut(edgeVar=:gathers, nodeVar=sensor:Sensor)
 |                                                          |
GetNodes(label=Route, var=route)                           GetNodes(label=Route, var=route)
