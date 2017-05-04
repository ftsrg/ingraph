MATCH (p1)-[k1:KNOWS]-(p2)
MATCH (p2)-[k2:KNOWS]-(p3)
RETURN p1, k1, p2, k2, p3
