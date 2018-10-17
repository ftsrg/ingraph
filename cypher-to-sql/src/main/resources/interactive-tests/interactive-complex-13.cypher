MATCH (person1:Person {id:32985348833679}), (person2:Person {id:26388279067108})
OPTIONAL MATCH path = shortestPath((person1)-[:KNOWS*]-(person2))
RETURN
CASE path IS NULL
  WHEN true THEN -1
  ELSE length(path)
END AS shortestPathLength;
