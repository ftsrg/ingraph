# smart transformation

Production([activeRoute])
 |
AntiJoin(activeRoute=inactiveRoute)
 | |
 | +---------------------------------------+
 |                                         |
 |                                        Filter(swP.position <> sw.currentPosition)
 |                                         |
 |                                        AllDifferent([_e1, _e2])
 |                                         |
 |                                        ExpandOut(edgeVar=_e2:target, vertexVar=sw:Switch)
 |                                         |
 |                                        ExpandOut(edgeVar=_e1:follows, vertexVar=swP:SwitchPosition)
 |                                         |
GetVertices(vertexVar=activeRoute:Route)  GetVertices(vertexVar=inactiveRoute:Route)

# plain version:


Production([activeRoute])
 |
NotIn(activeRoute) # variable not appear in 2nd input
 | |
 | +---------------------------------------+
 |                                         |
 |                                        NamedInnerProduction([inactiveRoute], name=inactiveRoutes)
 |                                         |
 |                                        Filter(swP.position <> sw.currentPosition)
 |                                         |
 |                                        AllDifferent([_e1, _e2])
 |                                         |
 |                                        ExpandOut(edgeVar=_e2:target, vertexVar=sw:Switch)
 |                                         |
 |                                        ExpandOut(edgeVar=_e1:follows, vertexVar=swP:SwitchPosition)
 |                                         |
GetVertices(vertexVar=activeRoute:Route)  GetVertices(vertexVar=inactiveRoute:Route)
