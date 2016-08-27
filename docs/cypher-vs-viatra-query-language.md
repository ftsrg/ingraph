# Differences between Cypher and the VIATRA Query Language

Both [Cypher](https://neo4j.com/docs/developer-manual/current/cypher/) and the [VIATRA Query Language](https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/QueryLanguage) offer a formalism for specifying graph patterns.

|                                 | Cypher                           | VIATRA Query Language    |
| ------------------------------- | -------------------------------- | ------------------------ |
| collection type for the result of a query/operation | list         | set                      |
| multiple edges                  | [allowed](multiple-edges.md)     | not allowed              |
| edges in matches                | [edges are required to be different](CITATION NEEDED) | edges are not required to be different |
| subpatterns                     | partially suppored using the [`WITH` keyword](https://neo4j.com/docs/developer-manual/current/cypher/#query-with) | supported with the `find` keyword |
