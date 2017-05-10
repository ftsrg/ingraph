MATCH (t:Train {number: 2})-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)
DELETE r
CREATE (t)-[:ON]->(seg2)
