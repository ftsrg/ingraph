MATCH (p:Person)
WITH p
UNWIND p.speaks AS lang
RETURN p.name, lang
