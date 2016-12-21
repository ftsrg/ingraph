MATCH (a)
WHERE a = a.val
RETURN count(a)
