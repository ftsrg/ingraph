MATCH (u:User {login: 'Michael'})-[:FRIEND]-()-[r:RATED]->(m:Movie)
RETURN m.title, avg(r.stars), count(*)
ORDER BY AVG(r.stars) DESC, count(*) DESC
