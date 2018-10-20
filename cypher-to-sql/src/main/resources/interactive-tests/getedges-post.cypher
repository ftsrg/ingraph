MATCH ()-[:LIKES]->(m:Post)
RETURN m.id
ORDER BY m.id
