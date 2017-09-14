# LDBC Social Network Benchmark – Business Intelligence workload

* [Specification](http://wiki.ldbcouncil.org/display/TUC/Business+Intelligence+Workload)

## Features used by the queries

| query                 | [01](bi-1.cypher) | [02](bi-2.cypher) | [03](bi-3.cypher) | [04](bi-4.cypher) | [05](bi-5.cypher) | [06](bi-6.cypher) | [07](bi-7.cypher) | [08](bi-8.cypher) | [09](bi-9.cypher) | [10](bi-10.cypher) |
| --------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| variable length path  |     |     |     |     |     | x   |     | x   |     |     |
| `CASE`                | x   |     |     |     |     |     |     |     |     | x   |
| aggregation           | x   | x   | x   | x   | x   | x   | x   |     | x   | x   |


| query                 | [11](bi-11.cypher) | [12](bi-12.cypher) | [13](bi-13.cypher) | [14](bi-14.cypher) | [15](bi-15.cypher) | [16](bi-16.cypher) | [17](bi-17.cypher) | [18](bi-18.cypher) | [19](bi-19.cypher) | [20](bi-20.cypher) |
| --------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| variable length path  |     |     |     | x   |     | x   |     |     |     | x   |
| `CASE`                |     |     |     |     |     |     |     |     |     |     |
| aggregation           | x   | x   | x   | x   | x   |     |     |     | x   | x   |


| query                 | [21](bi-21.cypher) | [22](bi-22.cypher) | [23](bi-23.cypher) | [24](bi-24.cypher) | [25](bi-25.cypher) |
| --------------------- | --- | --- | --- | --- | --- |
| variable length path  |     |     |     |     |     |
| `CASE`                |     |     |     |     |     |
| aggregation           |     |     | x   | x   |     |
