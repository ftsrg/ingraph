// Tag evolution
MATCH (tag:Tag)<-[:hasTag]-(message:Message)
RETURN tag.name, count(message) AS countMonth
ORDER BY tag.name
