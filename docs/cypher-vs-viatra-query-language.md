# Differences between Cypher and the VIATRA Query Language

Both [Cypher](https://neo4j.com/docs/developer-manual/current/cypher/) and the [VIATRA Query Language](https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/QueryLanguage) (VQL) offer a formalism for specifying graph patterns.

|                                 | Cypher                           | VIATRA Query Language    |
| ------------------------------- | -------------------------------- | ------------------------ |
| accessing types from queries    | can use the `type()` of vertices of edges in the query } | "metaqueries" are not supported |
| data model                      | property graph ([paper](http://arxiv.org/abs/1006.2361), [Neo4j documentation](https://neo4j.com/docs/developer-manual/current/introduction/#graphdb-concepts), [TinkerPop3 Graph Structure](http://tinkerpop.apache.org/docs/current/reference/#_the_graph_structure)) | [EMF](https://eclipse.org/modeling/emf/) |
| model schema                    | not known in advance             | known in advance (EMF)   |
| unique identifiers              | both vertices and edges have unique identifiers which can be accessed with the [`ID()`](https://neo4j.com/docs/developer-manual/current/cypher/#functions-id) method [1] | not required (similarly to the OO data model, objects/references have no ids by default)             |
| collection type for the result of a query/operation | set/multiset/list         | set                      |
| multiple edges                  | [allowed](multiple-edges.md)     | not allowed              |
| edges in matches                | [edges are required to be different](http://neo4j.com/docs/developer-manual/current/cypher/#cypherdoc-uniqueness) in a single `MATCH` clause | edges are not required to be different |
| subpatterns                     | partially supported using the [`WITH` keyword](https://neo4j.com/docs/developer-manual/current/cypher/#query-with) | supported with the [`find` keyword](https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/QueryLanguage#Advanced_Pattern_Constraints) |
| negative subpatterns            | `WHERE NOT ... IN ...`, `WHERE NOT ... <pattern>` | [`neg find`](https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/QueryLanguage#Advanced_Pattern_Constraints) |
| results                         | vertices, edges and attributes | objects and attributes |

[1] Both 1) the TinkerGraph property graph data model requires unique identifiers for [vertices](http://tinkerpop.apache.org/javadocs/3.1.3/full/org/apache/tinkerpop/gremlin/structure/Vertex.html) and [edges](http://tinkerpop.apache.org/javadocs/3.1.3/full/org/apache/tinkerpop/gremlin/structure/Edge.html) and the 2) Neo4j data model requires identifiers for [nodes and relationships](https://neo4j.com/docs/developer-manual/current/cypher/#functions-id).
