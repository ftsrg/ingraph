MATCH (n)
RETURN labels(n)[0] as type, count(*) as count, collect(n.host) as names
