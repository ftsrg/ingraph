MATCH
  (t1:Train)-[:ON]->(seg1:Element)-[:NEXT*1..2]-
  (seg2:Element)<-[:ON]-(t2:Train)
RETURN t1, t2, seg1, seg2
