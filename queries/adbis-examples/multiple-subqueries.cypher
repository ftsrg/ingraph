MATCH (m1:Message)
WITH m1.language AS singleLang, count(*) AS cnt
WHERE cnt = 1
MATCH (m2:Message)
WHERE m2.language = singleLang
OPTIONAL MATCH (m2)-[:REPLY_OF]->(m3:Message)
RETURN m2.language as reply, m3.language as orig
