# LDBC Social Network Benchmark

The Cypher implementations are taken from the [LDBC specification v0.2.2](https://github.com/ldbc/ldbc_snb_docs/blob/master/LDBC_SNB_v0.2.2.pdf)

## Paths and legacy Cypher features used by the queries

| query                             |  [1](query-1.cypher) |  [2](query-2.cypher) |  [3](query-3.cypher) |  [4](query-4.cypher) |  [5](query-5.cypher) |  [6](query-6.cypher) |  [7](query-7.cypher) |  [8](query-8.cypher) |  [9](query-9.cypher) | [10](query-10.cypher) | [11](query-11.cypher) | [12](query-12.cypher) | [13](query-13.cypher) | [14](query-14.cypher) |
| ------------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| variable length path            |  x  |     |  x  |     |  x  |  x  |     |     |  x  |  x  | x   | x   |     |     |
| `CASE`                          |  x  |  x  |     |     |     |     |     |     |  x  |     |     |     | x   |     |
| `shortestPath`                  |     |     |     |     |     |     |     |     |     |     |     |     | x   |     |
| `allShortestPaths`              |     |     |     |     |     |     |     |     |     |     |     |     |     | x   |
| `reduce`                        |     |     |     |     |     |     |     |     |     |     |     |     |     | x   |

Technically, Q10 has a variable length path, but the length of the path is `2..2`.

## References

* [Official publication list](http://ldbcouncil.org/publications)
* [SIGMOD paper](http://dl.acm.org/citation.cfm?id=2742786)
* [FP7 Deliverable](http://ldbcouncil.org/sites/default/files/LDBC_D3.3.34.pdf)
* [FP7 Deliverable / Appendix](http://ldbcouncil.org/sites/default/files/LDBC_D3.3.34_appendix.pdf)
