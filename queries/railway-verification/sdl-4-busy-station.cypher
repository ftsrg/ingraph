MATCH (s:Station)-[:INCL]->(:Element)<-[:ON]-(tr:Train)
WITH s, count(tr) AS countTrains
WHERE countTrains >= 2
RETURN s
