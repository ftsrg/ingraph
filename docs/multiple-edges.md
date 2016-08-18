# Multiple edges

Neo4j Cypher allows multiple edges. To test, issue the following commands on an empty database:

```
CREATE (a:A), (b:B),
(a)-[:w]->(b),
(a)-[:w]->(b)
```

Query the results with:

```
MATCH (n)
RETURN n
```
