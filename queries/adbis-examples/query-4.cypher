MATCH (p1:Person)
WHERE (p1:Student) OR (p1:Teacher)
UNWIND p1.speaks AS p1lang
MATCH (p2:Person)
WHERE p1 <> p2 AND p1lang IN p2.speaks
RETURN p1.name, p1lang, p2
