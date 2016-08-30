MATCH (n)
WHERE 'a' < n.value <= 'c'
RETURN n.value
