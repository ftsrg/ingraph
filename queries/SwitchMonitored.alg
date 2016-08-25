Distinct([sw]) # distinct can be ignored by a smart optimizer
 |
AntiJoin([sw]) # antijoin on the listed variables that appear on both sides
 | |
 | +----------------------------------------------------+
 |                                                      |
 |                                                     ExpandOut(edgeVar=:monitoredBy, nodeVar=:Sensor)
 |                                                      |
GetNodes(label=Switch, var=sw)                         GetNodes(label=Switch, var=sw)
