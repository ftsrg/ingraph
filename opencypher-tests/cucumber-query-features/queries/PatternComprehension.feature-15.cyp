MATCH (n)
RETURN [(n)-[r:T]->() | r.prop] AS list