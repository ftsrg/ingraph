# Cypher To SQL

## Transpile LDBC queries from Cypher to SQL
- Here you can define the running queries to be transpiled: [LdbcTest.scala:110](https://github.com/FTSRG/ingraph/blob/cypher-to-sql/cypher-to-sql/src/test/scala/ingraph/compiler/sql/driver/LdbcTest.scala#L110)
- Run from root directory
  - `./gradlew :cypher-to-sql:test --tests ingraph.compiler.sql.driver.LdbcParameterizedQueriesTest`
  - `cypher-to-sql/ldbc_snb_implementations/postgres/queries-generated` folder contains the generated queries

## Filter passed scenarios from test result XML
`sed -rn 's/^.*Scenario &quot;(.+)&quot;" status="passed".*$/"\1",/pg' | sort`
