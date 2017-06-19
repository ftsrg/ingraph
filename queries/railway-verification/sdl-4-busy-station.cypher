MATCH (s:Station)-[:INCLUDES]->(:RailroadElement)<-[:ON]-(t:Train)
WITH s, count(t) AS countTrains
WHERE countTrains >= 2
RETURN s
