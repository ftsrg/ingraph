MATCH (p:Person)-[:LIKES]->(m:Message)
RETURN p.name, m.language
