MATCH ()-[:LIKES]->(m:Comment)
RETURN m.id
ORDER BY m.id
