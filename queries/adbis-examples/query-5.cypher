MATCH (p1:Person)-[ks:KNOWS*2..2]->(p2:Person)
RETURN p1, p2
