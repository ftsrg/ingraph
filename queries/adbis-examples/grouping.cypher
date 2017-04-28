MATCH (p:Person)
WITH p
UNWIND p.speaks AS language
RETURN language, COUNT(DISTINCT p.name) as cnt
