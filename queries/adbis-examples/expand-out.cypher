MATCH (p1:Person)-[:KNOWS]->(p2:Person)
RETURN p1.name, p2.name
