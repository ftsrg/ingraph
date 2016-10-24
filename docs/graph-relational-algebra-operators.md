# Relational algebra operators

List of graph relational operators with examples.

## Nullary Operators

* `GetVertices(vertexVar=inactiveRoute:Route)`

## Unary operators

* `AllDifferent([_e1, _e2])`
* `Distinct([segment, segment.length AS length])`
* `Expand(edgeVar=_e1:follows, vertexVar=swP:SwitchPosition)`
* `ExpandIn(edgeVar=_e1:monitoredBy, vertexVar=segment1:Segment)`
* `ExpandOut(edgeVar=_e2:target, vertexVar=sw:Switch)`
* `Filter(segment.length <= 0)`
* `Filter(swP.position != sw.currentPosition)`
* `NamedInnerProduction([inactiveRoute], name=inactiveRoutes)`
* `NamedSubExpr(ex7)`
* `Production([activeRoute])`
* `Trimmer`

## Binary operators

* `AntiJoin([route, sensor])`
* `AntiJoin(activeRoute=inactiveRoute)`
* `NaturalJoin`
* `NotIn(activeRoute)` (variable not appear in 2nd input)
