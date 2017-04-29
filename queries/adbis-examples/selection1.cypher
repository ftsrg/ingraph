MATCH (p1:Person)-[k:KNOWS]-(p2:Person)
WHERE k.since < 2000
RETURN p1.name, p2.name
