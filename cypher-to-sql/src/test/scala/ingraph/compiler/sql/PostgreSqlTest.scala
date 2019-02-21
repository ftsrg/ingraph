package ingraph.compiler.sql

import org.scalatest.FunSuite

class PostgreSqlTest extends FunSuite {
  test("Run Embedded PostgreSQL Server") {
    PostgreSqlMain.main(Array())
  }
}
