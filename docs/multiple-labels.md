# Multiple labels

Neo4j Cypher allows multiple labels at some uses.

## Vertex labels

Vertices can have **any number of labels** (0, 1 or more)

```
CREATE (n1:A:B {desc: "n1: A,B"})
     , (n2:B:C {desc: "n2: B,C"})
     , (n3 {desc: "n3: no label"})
```

At **query-time**, multiple labels given are **AND**-ed together, but need not to match the full set of labels associated to a vertex (see: 1, 3 below).

| ordinal | query | resulting vertices |
| :-----: | ----- | :----------------: |
| 1 | `MATCH (n) RETURN n` | `n1, n2, n3` |
| 2 | `MATCH (n:A:B) RETURN n` | `n1` |
| 3 | `MATCH (n:B) RETURN n` | `n1, n2` |
| 4 | `MATCH (n:A:B:C) RETURN n` | _empty result_ |

## Edge labels

Edges have one and only **one label at create time**, fo the 1st example below will suceed,
where the 2nd and 3rd will fail saying
`A single relationship type must be specified for CREATE`.

1. `CREATE (n1)-[:EL1]->(n2)`
2. `CREATE (n1)-[:EL1|EL2]->(n2)`
3. `CREATE (n1)-[]->(n2)`

At **query-time**, multiple labels given for a relationships are **OR**-ed together.

```
CREATE (n {desc:"n"})-[e1:EL1 {desc: "e1: EL1"}]->(n1 {desc: "n1"})
     , (n)-[e2:EL2 {desc: "e2: EL2"}]->(n2 {desc: "n2"})
     , (n)-[e3:EL3 {desc: "e3: EL3"}]->(n3 {desc: "n3"})
```

The following query will return `e1` and `e3`:

```
MATCH (n1)-[e:EL1|EL3]->(n2)
RETURN e, n1, n2
```
