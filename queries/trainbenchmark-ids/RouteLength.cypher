MATCH
  (route:Route)-[:follows]->(swP1:SwitchPosition)-[:target]->(sw1:Switch),
  (route)-[:follows]->(swP2:SwitchPosition)-[:target]->(sw2:Switch)
WHERE sw1.id < sw2.id
MATCH p=(sw1)-[:connectsTo*]-(sw2)
WITH route, nodes(p) AS tes
// only the start and the end of the path are switches
WHERE size(filter(te IN tes WHERE NOT (te:Segment))) = 2
UNWIND tes AS te
RETURN route.id, sum(te.length) AS length
