// Tag evolution
MATCH (tag:Tag)<-[:hasTag]-(message:Message)
RETURN tag, count(message) AS countMonth1, length(tag.name) AS tn
ORDER BY tag.name
