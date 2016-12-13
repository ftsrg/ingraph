MATCH (:MetaInfo)-[:lastCommit]->(c:Commit)
RETURN c.hash as commitHash