// Q13. Single shortest path. Given PersonX and PersonY, find the shortest path between them in the subgraph induced by the Knows relationships. Return the length of this path.
MATCH (person1:Person {id: $person1}), (person2:Person {id: $person2})
OPTIONAL MATCH path = shortestPath((person1)-[:knows]-(person2))
RETURN length(path) AS pathLength
