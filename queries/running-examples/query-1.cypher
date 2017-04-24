MATCH (p:Person)-[:KNOWS]-(:Person)-[:LIKES]->(m:Message)
RETURN p, m
