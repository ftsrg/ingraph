MATCH (n)
SET n.array = [1, 2, 3, 4, 5]
RETURN tail(tail(n.array))