MERGE (:MetaInfo)-[:lastCommit]->(c:Commit)
SET c.hash = {commithash}