MATCH
  (t:Train)-[:ON]->(seg:Segment)
OPTIONAL MATCH
  (seg)-[:NEXT|OUT*0..]-(:Segment)<-[:INCL]-(st:Station)
RETURN DISTINCT t, st
