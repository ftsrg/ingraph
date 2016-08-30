# Cypher queries


Get the Cypher queries from the Neo4j documentation.
```
curl -s https://neo4j.com/docs/developer-manual/current/cypher/ | pcregrep -M -o '<code data-lang="cypher">(\n|.)*?</code>' | sed 's|<code data-lang="cypher">||' | sed 's|</code>|\n|'
```

## LDBC Social Network Benchmark

Used in [An SQL-Based Query Language and Engine for Graph Pattern Matching](https://scholar.google.hu/citations?view_op=view_citation&hl=en&user=665djsIAAAAJ&cstart=20&pagesize=80&citation_for_view=665djsIAAAAJ:iH-uZ7U-co4C).

* [The Cypher queries of the LDBC Social Network Benchmark](https://github.com/bme-db-lab/ingraph/tree/master/queries/ldbc-snb)

