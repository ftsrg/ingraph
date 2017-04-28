MATCH (p:Person)
WITH p
UNWIND p.speaks AS language
RETURN p.name, language
