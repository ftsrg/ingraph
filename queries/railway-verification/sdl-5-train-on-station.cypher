MATCH
  (t:Train)-[:ON]->(seg:Segment)
OPTIONAL MATCH
  (seg)-[:NEXT|WAY_OUT*0..]-(:Segment)<-[:INCLUDES]-(st:Station)
RETURN DISTINCT t, st
