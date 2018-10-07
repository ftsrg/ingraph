# Semantics of `OPTIONAL MATCH`

Toy data set:

```
CREATE
  (:X {val: 1})-[:E]->(:Y {val: 2}),
  (:X {val: 3})
```

It is possible to use both two sides of the 'join' (i.e. existing variables and potentially null variables) into the `WHERE` clause:

```
MATCH (x:X)
OPTIONAL MATCH (x)-[:E]->(y:Y)
WHERE x.val < y.val
RETURN x, y
```

```
MATCH (x:X)
OPTIONAL MATCH (x)-[:E]->(y:Y)-[:F]->(z:Z)
WHERE x.val < z.val
RETURN x, y, z
```

Neo4j has an [`Optional`](https://neo4j.com/docs/developer-manual/current/cypher/execution-plans/operators/#query-plan-optional) operator to handle this.

For a more detailed discussion on this, visit https://github.com/opencypher/openCypher/issues/191.
