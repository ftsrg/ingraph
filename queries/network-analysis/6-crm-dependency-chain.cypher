MATCH (dependency)<-[:DEPENDS_ON*]-(dependent)
WITH dependency, count(DISTINCT dependent) AS Dependents
ORDER BY Dependents DESC
LIMIT 1
WITH dependency
MATCH p=(resource)-[:DEPENDS_ON*]->(dependency)
WHERE resource.system = 'CRM'
RETURN "[" + head(nodes(p)).host + "]" + reduce(s = "", n in tail(nodes(p)) | s + " -> " + "[" + n.host + "]") AS Chain
