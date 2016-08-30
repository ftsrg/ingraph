MATCH (a)-[r]->(b)
WHERE r.foo = $param
RETURN b
