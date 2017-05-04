MATCH (u:User {login: 'Michael'})-[r:RATED]->(m:Movie)
WHERE r.stars > 3
RETURN m.title, r.stars, r.comment
