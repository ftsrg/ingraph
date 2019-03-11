MATCH (p1:Person)<-[k:KNOWS*1..1]-(p2:Person)
RETURN p1.id, p2.id
ORDER BY p1.id, p2.id
LIMIT 20
