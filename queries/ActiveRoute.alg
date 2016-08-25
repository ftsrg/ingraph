# smart transformation

Production([activeRoute])
 |
AntiJoin(activeRoute=inactiveRoute)
 | |
 | +-------------------------------------+
 |                                       |
 |                                      Filter(swP.position != sw.currentPosition)
 |                                       |
 |                                      AllDifferent([_e1, _e2])
 |                                       |
 |                                      ExpandOut(edgeVar=_e2:target, nodeVar=sw:Switch)
 |                                       |
 |                                      Expand(edgeVar=_e1:follows, nodeVar=swP:SwitchPosition)
 |                                       |
GetNodes(label=Route, var=activeRoute)  GetNodes(label=Route, var=inactiveRoute)

# plain version:


Production([activeRoute])
 |
NotIn(activeRoute) # variable not appear in 2nd input
 | |
 | +-------------------------------------+
 |                                       |
 |                                      NamedInnerProduction([inactiveRoute], name=inactiveRoutes)
 |                                       |
 |                                      Filter(swP.position != sw.currentPosition)
 |                                       |
 |                                      AllDifferent([_e1, _e2])
 |                                       |
 |                                      ExpandOut(edgeVar=_e2:target, nodeVar=sw:Switch)
 |                                       |
 |                                      Expand(edgeVar=_e1:follows, nodeVar=swP:SwitchPosition)
 |                                       |
GetNodes(label=Route, var=activeRoute)  GetNodes(label=Route, var=inactiveRoute)

