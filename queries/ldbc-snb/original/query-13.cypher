// Q13. Single shortest path. Given PersonX and PersonY, find the shortest path between them in the subgraph induced by the Knows relationships. Return the length of this path.
MATCH (person1:Person {id:{1}}), (person2:Person {id:{2}})
OPTIONAL MATCH path = shortestPath((person1)-[:KNOWS]-(person2))
RETURN
  CASE path IS NULL
    WHEN true THEN -1
    ELSE length(path)
  END AS pathLength
