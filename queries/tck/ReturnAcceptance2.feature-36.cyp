MATCH (n)
RETURN count(DISTINCT {foo: n.list}) AS count
