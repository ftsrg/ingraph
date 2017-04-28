MATCH
  ()<-[:LIKES]-(m:Message)-[:LIKES]->(),
  (m)<-[:REPLY]-(r)
RETURN r
