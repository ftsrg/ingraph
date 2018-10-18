# Cypher To SQL

## Transpile LDBC queries from Cypher to SQL
- Here you can define the running queries to be transpiled: [LdbcTest.scala:110](https://github.com/FTSRG/ingraph/blob/cypher-to-sql/cypher-to-sql/src/test/scala/ingraph/compiler/sql/driver/LdbcTest.scala#L110)
- Run from root directory
  - `./gradlew :cypher-to-sql:test --tests ingraph.compiler.sql.driver.LdbcParameterizedQueriesTest`
  - `cypher-to-sql/ldbc_snb_implementations/postgres/queries-generated` folder contains the generated queries
