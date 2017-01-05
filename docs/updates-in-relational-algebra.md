# Updates in relational algebra

The standard relational algebra does not offer operators for updating relations.

As a possible extenson, the [assignment operation](http://codex.cs.yale.edu/avi/db-book/db5/slide-dir/ch2.pdf) `←`, as defined in the [Database System Concepts](http://codex.cs.yale.edu/avi/db-book/) book (Silberschatz, Korth, Sudarshan) allows to assign a new value to relations.

`CREATE` and `DELETE` expressions may be expressed as `r ← r ∪ Δr` and `r ← r − Δr`.

## Updates in Rete

Updates can be expressed with a Rete network with the following approach:

* The pattern defined in the `MATCH` part of the query is evaluated with a Rete network.
* The results are retrieved from the production node (in a single step, we do not allow updates for this pattern).
* The resulting tuples are projected and propagated to the base relations, i.e. the `GetVertices` and `GetEdges` nodes of the Rete network.

For example, this query adds a `WORKED_TOGETHER` edge between actors who played together in a movie.

```
MATCH (a1:Actor)-[:ACTED_IN]->(m:Movie)<-[:ACTED_IN]-(a2:Actor)
CREATE (a1)-[:WORKED_TOGETHER]->(a2)
```

Usually, there is no `RETURN` expression in update queries, so the `MATCH` returns `a1`, `m`, `a2` tuples. These are projected to `a1`, `a2` tuples and propagated to the `WORKED_TOGETHER` base relation (a `GetEdges` operator).

## Open challenges

* Creating new types.
