MATCH (p:Person)
UNWIND p.speaks AS language
RETURN DISTINCT language
ORDER BY language
