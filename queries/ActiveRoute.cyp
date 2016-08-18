MATCH (inactiveRoute:Route)-[:follows]-(swP:SwitchPosition)-[:target]->(sw:Switch)
WHERE swP.position <> sw.currentPosition
WITH collect(inactiveRoute) AS inactiveRoutes

MATCH (activeRoute:Route)
WHERE NOT activeRoute IN inactiveRoutes

RETURN activeRoute
