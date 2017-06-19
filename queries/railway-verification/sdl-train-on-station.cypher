MATCH
  (t:Train)-[:ON]->(seg:Segment)
OPTIONAL MATCH
  (seg)-[:NEXT|TOP|STRAIGHT|DIVERGING*0..]-(:Segment)<-[:INCLUDES]-(st:Station)
RETURN t, st
