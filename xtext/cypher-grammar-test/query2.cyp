MATCH (a)<-[]-(c), (a)-[r:T]->(b)
RETURN x
UNION
MATCH (a)<-[]-(c), (a)-[r:T]->(b)
RETURN x
