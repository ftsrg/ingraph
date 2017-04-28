MATCH (p1)-[:KNOWS]-(p2)
MATCH (p2)-[:KNOWS]-(p3)
RETURN p1, p2, p3
