# Semantics of list comprehensions for pattern matching

```
CREATE
  (cA:City {name: 'New York'}),
  (cB:City {name: 'Montreal'})<-[:LIVES_IN]-(pA:Person {name: 'A'}),
  (cC:City {name: 'Budapest'})<-[:LIVES_IN]-(pB:Person {name: 'B'})-[:LIKES]->(m:Movie {name: 'The Usual Suspects'})
```

```
MATCH (c:City)
OPTIONAL MATCH (c)<-[:LIVES_IN]-(p:Person)
WITH c, collect(p) AS persons
RETURN c.name, length([p IN persons WHERE (p)-[:LIKES]->(:Movie)]) AS movieLoverCount
```

This can be translated to the following expression:

```
MATCH (c:City)                              // 1
OPTIONAL MATCH (c)<-[:LIVES_IN]-(p1:Person) // 2
WITH c, collect(p1) AS persons              // 3
UNWIND persons + [null] AS p2               // 4
WITH c, p2                                  // 5
WHERE (p2)-[:LIKES]->(:Movie) OR p2 IS NULL // 6
RETURN c.name, count(p2) AS movieLoverCount // 7
```

* Omitting the `+ [null]` in line 4 loses cities without inhabitants.
* Omitting the `OR p2 IS NULL` in line 6 also loses cities without inhabintants.
* Using `OPTIONAL MATCH` instead of `WHERE` returns "Montreal" with a `movieLoverCount` of 1 (as `p2` is indeed non-null, even though the `Movie` node does not match.

The [`OPTIONAL UNWIND` Cypher Improvement Request (CIR-2017-234)](https://github.com/opencypher/openCypher/issues/234) would be interesting for this case.
