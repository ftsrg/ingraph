// close proximity
//MATCH (t1:Train)-[:ON]->(seg1:Segment)-[:NEXT*1..2]->(seg2:Segment)<-[:ON]-(t2:Train)
//MATCH (t1:Train)-[:ON]->(seg1:Segment)-[:NEXT]->()-[:NEXT]->(seg2:Segment)<-[:ON]-(t2:Train)
MATCH (t1:Train)-[:ON]->(seg1:Segment)-[:NEXT]->()-[:NEXT]->()-[:NEXT]->(seg2:Segment)<-[:ON]-(t2:Train)
RETURN t1.number, t2.number, seg1.name, seg2.name
