Distinct([semaphore, route, swP, sw, sw.currentPosition AS currentPosition, swP.position AS position]) # TODO: what does it mean to return a complete node/edge?
 |
Filter(sw.currentPosition != swP.position)
 |
Filter(semaphore.signal='GO')
 |
AllDifferent([_e1, _e2, _e3])
 |
ExpandOut(edgeVar=_e3:target, nodeVar=sw:Switch)
 |
ExpandOut(edgeVar=_e2:follows, nodeVar=swP:SwitchPosition)
 |
ExpandIn(edgeVar=_e1:entry, nodeVar=route:Route)  # as edge is not referenced, no edgeVar is unnamed, but has label constraint
 |
GetNodes(label=Semaphore, var=semaphore)
