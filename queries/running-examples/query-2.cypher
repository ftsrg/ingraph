MATCH (person)-[:LIKES]->(post:Message:Post)<-[:REPLY_OF*0..]-(m:Message)
WHERE NOT m.language IN person.speaks
RETURN person, collect(m) AS messagesNotSpoken
