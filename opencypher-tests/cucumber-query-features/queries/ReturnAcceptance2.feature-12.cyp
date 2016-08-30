MATCH (n)
SET n.x = [1, 2, 3]
RETURN size(n.x)