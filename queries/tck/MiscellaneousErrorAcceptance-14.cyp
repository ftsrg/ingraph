MATCH (n)
MATCH (n)-[r*]->()
WHERE r.foo = 'apa'
RETURN r
