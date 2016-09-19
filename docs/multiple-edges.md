# Multiple edges

Neo4j Cypher allows multiple edges. To test, issue the following commands on an empty database:

```
CREATE (a:A {desc: "a: A"}), (b:B {desc: "b: B"})
     , (a)-[:w {desc: "_e1: w"}]->(b)
     , (a)-[:w {desc: "_e2: w"}]->(b)
```

Query the results with:

```
MATCH (n)
RETURN n
```
