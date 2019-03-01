// vertex
MATCH (n)
RETURN id(n) AS vertex_id

// edge
MATCH (from)-[edge]->(to)
RETURN id(edge) AS edge_id, id(from) AS from, id(to) AS to, type(edge) AS type

// label
MATCH (n)
UNWIND labels(n) AS name
RETURN id(n) AS parent, name

// vertex_property
MATCH (n)
UNWIND keys(n) AS key
RETURN id(n) AS parent, key, properties(n)[key] AS value

// edge_property
MATCH ()-[e]->()
UNWIND keys(e) AS key
RETURN id(e) AS parent, key, properties(e)[key] AS value