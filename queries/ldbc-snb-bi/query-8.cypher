MATCH (tag:Tag)<-[:hasTag]-(:Message)<-[:replyOf*]-(comment:Comment)-[:hasTag]->(relatedTag:Tag)
WHERE NOT (comment)-[:hasTag]->(tag)
RETURN relatedTag.name, count(comment) AS count
ORDER BY count DESC, relatedTag.name ASC
LIMIT 100
