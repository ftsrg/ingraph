// trailing the Switch
MATCH (t:Train)-[:ON]->(seg:Segment)<-[:STRAIGHT]-(sw:Switch)
WHERE sw.position = 'diverging'
RETURN t.number, sw.id
