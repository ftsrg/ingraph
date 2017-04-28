MATCH (person)-[:LIKES]->(post:Message:Post)<-[:REPLY_OF*0..]-(m:Message)
WHERE m.language IN person.speaks
RETURN person, m
