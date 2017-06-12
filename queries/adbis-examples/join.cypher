MATCH
 ()-[:LIKES]->(m:Message)<-[:LIKES]-(),
 (m)<-[:REPLY_OF]-(r)
RETURN r
