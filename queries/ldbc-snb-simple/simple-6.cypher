MATCH (p1:Person)-[:knows*]->(p2:Person)
WHERE p1.id = 41
RETURN p1.id, p2.id
ORDER BY p1.id, p2.id
