MATCH (p1:Person)-[k1:KNOWS]->(p2:Person)-[k2:KNOWS]->(p3:Person)
RETURN p1.name, p2.name, p3.name
