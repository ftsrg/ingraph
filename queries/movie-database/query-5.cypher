MATCH (a:Actor)-[:ACTS_IN]->(m:Movie)
WITH a, count(m) AS acted
WHERE acted >= 10
WITH a, acted
MATCH (a:Director)-[:DIRECTED]->(m:Movie)
WITH a, acted, collect(m.title) AS directed
WHERE length(directed) >= 2
RETURN a.name, acted, directed
ORDER BY length(directed) DESC, acted DESC
