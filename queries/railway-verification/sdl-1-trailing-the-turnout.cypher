MATCH (tr:Train)-[:ON]->(seg:Segment)-[n:WAY_OUT]-(tu:Turnout)
WHERE tu.position <> n.direction
RETURN tr, tu, n, seg
