MATCH (n)
RETURN n.prop1
ORDER BY max(n.prop2)