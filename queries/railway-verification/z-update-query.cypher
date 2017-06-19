MATCH (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)
WHERE t.number = 2
DELETE r
CREATE (t)-[:ON]->(seg2)
