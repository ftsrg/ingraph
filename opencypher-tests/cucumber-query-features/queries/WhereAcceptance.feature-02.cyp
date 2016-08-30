MATCH (n:Label)
WHERE n.prop < 10
RETURN n.prop AS prop
