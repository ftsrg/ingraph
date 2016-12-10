MATCH (u:User)-[r:RATED]->(m:Movie)<-[r2:RATED]-(likeminded),
(u)-[:FRIEND]-(friend)
WHERE r.stars > 3 AND r2.stars >= 3
RETURN likeminded, count(*)
ORDER BY count(*) desc LIMIT 10
