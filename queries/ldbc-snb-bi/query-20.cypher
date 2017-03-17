UNWIND $tagClasses AS tagClassName
MATCH (tagClass:TagClass {name: tagClassName})<-[:HAS_TYPE]-(:Tag)<-[:IS_SUBCLASS_OF*0..]-(tag:Tag)<-[:HAS_TAG]-(message:Message)
RETURN tagClass.name, count(message) AS postCount
ORDER BY postCount DESC, tagClass.name ASC
LIMIT 100
