package hu.bme.mit.oc2sql

import java.sql.DriverManager

import org.scalatest.FunSuite

class SqliteTest extends FunSuite {

  test("sqlite") {
    val connection = DriverManager.getConnection("jdbc:sqlite::memory:")
    val statement = connection.createStatement
    statement.setQueryTimeout(3600)

    val createStatement = connection.prepareStatement("CREATE TEMP TABLE IF NOT EXISTS Variables (Name TEXT PRIMARY KEY, Value LONG);")
    createStatement.execute
  }

}
