MATCH (tr:Train)-[:ON]->(seg:Segment)-[n:OUT]-(tu:Turnout)
WHERE tu.position <> n.direction
RETURN tr, tu, n, seg
