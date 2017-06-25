MATCH (p:Person) WITH p
UNWIND p.speaks AS language
RETURN language,
 count(DISTINCT p.name) as cnt
