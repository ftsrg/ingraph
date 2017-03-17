MATCH (tag:Tag {id: $tag})<-[:HAS_TAG]-(:Message)<-[:IN_REPLY_OF*]-(comment:Comment)-[:HAS_TAG]->(relatedTag:Tag)
WHERE NOT (comment)-[:HAS_TAG]->(tag)
RETURN relatedTag.name, count(comment) AS count
ORDER BY count DESC, relatedTag.name ASC
LIMIT 100
