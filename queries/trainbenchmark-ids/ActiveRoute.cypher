MATCH (route:Route {active: true})-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
WHERE swP.position <> sw.currentPosition
RETURN route.id, swP.id, sw.id
