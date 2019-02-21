MATCH ()-[:LIKES]->(m:Message)
RETURN m.id
ORDER BY m.id
