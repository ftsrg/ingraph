MATCH (p1:Person)-[ks:knows*]->(p2:Person)
WHERE p1.id = 41
RETURN p1.id, ks, p2.id
ORDER BY p1.id, p2.id
