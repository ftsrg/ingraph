//MATCH (comment:Comment)-[:hasTag]->(tag:Tag)
MATCH (tag:Tag)<-[:hasTag]-(comment:Comment)
RETURN comment.content, tag.name
