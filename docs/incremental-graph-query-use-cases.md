# Use cases for incremental graph queries

* **large graph:** the use case has the possibility of a large data model with 10M+ vertices/edges.
* **quick response time:** the query evaluation is required to be performed very quickly, e.g. in less than a second.
* **static graphs:** the queries can be formulated on the current "version" of the graph, i.e. time information are not required (timestamps and time windows are not used – as opposed to event streams and complex event processing).
* **FOL (first-order logic):** the queries do not require construct outside first order logic. For example, filtering and negation properties can expressed in first order logic, but Dijkstra's algorithm cannot.

| use case                           | large graph | quick response | static graphs | FOL |
| ---------------------------------- | :---------: | :------------: | :-----------: | :-: |
| model validation                   |      ✓      |        ✓       |        ✓      |  ✓  |
| source code static analysis        |      ✓      |        ✓       |        ✓      |  ✓  |
| fraud detection                    |      ✓      |        ✓       |        ✓      |  ✓  |
| model simulation                   |      ✓      |        ✓       |        ✓      |  ✓  |
| infrastructure / anomaly detection |      ✗      |        ✓       |        ✗      |  ✓  |
| runtime verification               |      ✓      |        ✓       |        ?      |  ✓  |
| recommendation                     |      ✓      |        ✗       |        ✓      |  ✓  |
| logistics (navigational patterns)  |      ✓      |        ✓       |        ✓      |  ✗  |

## Sources

* [Neo4j Use Cases](https://neo4j.com/use-cases/)
* [Graph Databases book](https://neo4j.com/book-graph-databases/)
* [Train Benchmark paper](https://inf.mit.bme.hu/en/research/publications/train-benchmark-cross-technology-performance-evaluation-continuous-model-valid)
