package ingraph.compiler.sql.driver

import org.scalatest.Suite

trait SharedSqlDriver extends SharedDriver[SqlDriver, SqlSession] {
  this: Suite =>

  override def initNewDriver(): SqlDriver = new SqlDriver(translateCreateQueries = true)
}
