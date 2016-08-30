OPTIONAL MATCH (a:DoesNotExist)
SET a += {prop: 42}
RETURN a
