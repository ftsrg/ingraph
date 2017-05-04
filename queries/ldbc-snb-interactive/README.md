# LDBC Social Network Benchmark – Interactive workload

The Cypher implementations are taken from the [LDBC specification v0.2.2](https://github.com/ldbc/ldbc_snb_docs/blob/bf8ead188f85639e203d4507cbade453c1298bee/LDBC_SNB_v0.2.2.pdf)

## Paths and legacy Cypher features used by the queries

| query                 | [1](original/query-1.cypher) | [2](original/query-2.cypher) | [3](original/query-3.cypher) | [4](original/query-4.cypher) | [5](original/query-5.cypher) | [6](original/query-6.cypher) | [7](original/query-7.cypher) | [8](original/query-8.cypher) | [9](original/query-9.cypher) | [10](original/query-10.cypher) | [11](original/query-11.cypher) | [12](original/query-12.cypher) | [13](original/query-13.cypher) | [14](original/query-14.cypher) |
| --------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| _standard variant_    | [1](query-1.cypher) | [2](query-2.cypher) | [3](query-3.cypher) | [4](query-4.cypher) | [5](query-5.cypher) | [6](query-6.cypher) | [7](query-7.cypher) | [8](query-8.cypher) | [9](query-9.cypher) |   [10](query-10.cypher) | [11](query-11.cypher) | [12](query-12.cypher) | [13](query-13.cypher) |  |
| variable length path  |  x  |     |  x  |     |  x  |  x  |     |     |  x  |  x  | x   | x   |     |     |
| [`CASE`](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-syntax-case) (originally)   |  x  |  x  |     |     |     |     |     |     |  x  |     |     |     | x   |     |
| [`shortestPath`](https://neo4j.com/docs/developer-manual/3.0/cypher/#_single_shortest_path)        |     |     |     |     |     |     |     |     |     |     |     |     | x   |     |
| [`allShortestPaths`](https://neo4j.com/docs/developer-manual/3.0/cypher/#_all_shortest_paths)    |     |     |     |     |     |     |     |     |     |     |     |     |     | x   |
| [`reduce`](https://neo4j.com/docs/developer-manual/3.0/cypher/#functions-reduce)              |     |     |     |     |     |     |     |     |     |     |     |     |     | x   |

Technically, Q10 has a variable length path, but the length of the path is `2..2`.

## "Standardized" and "globalized" versions

For simpler testing, we "globalized" the SNB queries, i.e. we removed parametric bindings to specific variables. For example, if a query started from a specific person, the globalized query start from all persons.

We removed queries using non-standard openCypher constructs (e.g. `allShortestPaths`), and changed parameters to use the standard `$param` notation instead of the deprecated `{param}` one.

## References

* [Official publication list](http://ldbcouncil.org/publications)
* [SIGMOD paper](http://dl.acm.org/citation.cfm?id=2742786)
* [FP7 Deliverable](http://ldbcouncil.org/sites/default/files/LDBC_D3.3.34.pdf)
* [FP7 Deliverable / Appendix](http://ldbcouncil.org/sites/default/files/LDBC_D3.3.34_appendix.pdf)
