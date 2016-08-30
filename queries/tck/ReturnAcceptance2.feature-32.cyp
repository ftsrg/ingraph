MATCH (a)
WITH a.a AS a, count(*) AS count
RETURN count
