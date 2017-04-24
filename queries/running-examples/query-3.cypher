MATCH (p:Person), (m:Message)
WHERE m.language IN p.speaks
RETURN p, m
