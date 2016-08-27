# Differences between Cypher and the VIATRA Query Language

Both [Cypher](https://neo4j.com/docs/developer-manual/current/cypher/) and the [VIATRA Query Language](https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/QueryLanguage) offer a formalism for specifying graph patterns.

|                                 | Cypher                           | VIATRA Query Language    |
| ------------------------------- | -------------------------------- | ------------------------ |
| data model                      | property graph ([paper](http://arxiv.org/abs/1006.2361), [Neo4j documentation](https://neo4j.com/docs/developer-manual/current/introduction/#graphdb-concepts), [TinkerPop3 Graph Structure](http://tinkerpop.apache.org/docs/current/reference/#_the_graph_structure)) | [EMF](https://eclipse.org/modeling/emf/) |
| collection type for the result of a query/operation | list         | set                      |
| multiple edges                  | [allowed](multiple-edges.md)     | not allowed              |
| edges in matches                | [edges are required to be different](CITATION NEEDED) | edges are not required to be different |
| subpatterns                     | partially suppored using the [`WITH` keyword](https://neo4j.com/docs/developer-manual/current/cypher/#query-with) | supported with the [`find` keyword](https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/QueryLanguage#Advanced_Pattern_Constraints) |
| results                         | vertices, edges and attributes | objects and attributes |
