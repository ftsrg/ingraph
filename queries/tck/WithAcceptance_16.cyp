MATCH (n)
WITH n
WHERE n.prop = 42
RETURN count(*)
