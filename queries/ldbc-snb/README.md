# LDBC Social Network Benchmark

The Cypher implementations are taken from the [LDBC specification v0.2.2](https://github.com/ldbc/ldbc_snb_docs/blob/master/LDBC_SNB_v0.2.2.pdf)

## Paths and legacy Cypher features used by the queries

|                                 |  Q1 |  Q2 |  Q3 |  Q4 |  Q5 |  Q6 |  Q7 |  Q8 |  Q9 | Q10 | Q11 | Q12 | Q13 | Q14 |
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
