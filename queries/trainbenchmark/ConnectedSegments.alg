Production([sensor, segment1, segment2, segment3, segment4, segment5, segment6])
 |
AllDifferent([_e1, _e2, _e3, _e4, _e5, _e6, _e7, _e8, _e9, _e10, _e11])
 |
NaturalJoin-LeftDeepTree
 |   |   |   |   |   |   |
ex1 ex2 ex3 ex4 ex5 ex6 ex7

NamedSubExpr(ex7)
 |
ExpandOut(edgeVar=_e11:monitoredBy, vertexVar=sensor:Sensor)
 |
GetVertices(vertexVar=segment6:Segment)

NamedSubExpr(ex6)
 |
ExpandOut(edgeVar=_e10:monitoredBy, vertexVar=sensor:Sensor)
 |
GetVertices(vertexVar=segment5:Segment)

NamedSubExpr(ex5)
 |
ExpandOut(edgeVar=_e9:monitoredBy, vertexVar=sensor:Sensor)
 |
GetVertices(vertexVar=segment4:Segment)

NamedSubExpr(ex4)
 |
ExpandOut(edgeVar=_e8:monitoredBy, vertexVar=sensor:Sensor)
 |
GetVertices(vertexVar=segment3:Segment)


NamedSubExpr(ex3)
 |
ExpandOut(edgeVar=_e7:monitoredBy, vertexVar=sensor:Sensor)
 |
GetVertices(vertexVar=segment2:Segment)

NamedSubExpr(ex2)
 |
ExpandOut(edgeVar=_e6:connectsTo, vertexVar=segment6:Segment)
 |
ExpandOut(edgeVar=_e5:connectsTo, vertexVar=segment5:Segment)
 |
ExpandOut(edgeVar=_e4:connectsTo, vertexVar=segment4:Segment)
 |
ExpandOut(edgeVar=_e3:connectsTo, vertexVar=segment3:Segment)
 |
ExpandOut(edgeVar=_e2:connectsTo, vertexVar=segment2:Segment)
 |
GetVertices(vertexVar=segment1:Segment)

NamedSubExpr(ex1)
 |
ExpandIn(edgeVar=_e1:monitoredBy, vertexVar=segment1:Segment)
 |
GetVertices(vertexVar=sensor:Sensor)
