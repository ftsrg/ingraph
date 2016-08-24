Production([sensor, segment1, segment2, segment3, segment4, segment5, segment6])
 |
AllDifferent([_e1, _e2, _e3, _e4, _e5, _e6, _e7, _e8, _e9, _e10, _e11])
 |
NaturalJoin-LeftDeepTree
 |   |   |   |   |   |   |
ex1 ex2 ex3 ex4 ex5 ex6 ex7

NamedSubExpr(ex7)
 |
ExpandOut(edgeVar=_e11:monitoredBy, nodeVar=sensor:Sensor)
 |
GetNodes(label=Segment, var=segment6)

NamedSubExpr(ex6)
 |
ExpandOut(edgeVar=_e10:monitoredBy, nodeVar=sensor:Sensor)
 |
GetNodes(label=Segment, var=segment5)

NamedSubExpr(ex5)
 |
ExpandOut(edgeVar=_e9:monitoredBy, nodeVar=sensor:Sensor)
 |
GetNodes(label=Segment, var=segment4)

NamedSubExpr(ex4)
 |
ExpandOut(edgeVar=_e8:monitoredBy, nodeVar=sensor:Sensor)
 |
GetNodes(label=Segment, var=segment3)


NamedSubExpr(ex3)
 |
ExpandOut(edgeVar=_e7:monitoredBy, nodeVar=sensor:Sensor)
 |
GetNodes(label=Segment, var=segment2)

NamedSubExpr(ex2)
 |
ExpandOut(edgeVar=_e6:connectsTo, nodeVar=segment6:Segment)
 |
ExpandOut(edgeVar=_e5:connectsTo, nodeVar=segment5:Segment)
 |
ExpandOut(edgeVar=_e4:connectsTo, nodeVar=segment4:Segment)
 |
ExpandOut(edgeVar=_e3:connectsTo, nodeVar=segment3:Segment)
 |
ExpandOut(edgeVar=_e2:connectsTo, nodeVar=segment2:Segment)
 |
getNodes(label=Segment, var=segment1)

NamedSubExpr(ex1)
 |
ExpandIn(edgeVar=_e1:monitoredBy, nodeVar=segment1:Segment)
 |
GetNodes(label=Sensor, var=sensor)
