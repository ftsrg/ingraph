MATCH (m:Message)<-[:LIKES]-(p1:Person)--(p2:Person)-[:LIKES]->(m)
RETURN p1, p2, m
