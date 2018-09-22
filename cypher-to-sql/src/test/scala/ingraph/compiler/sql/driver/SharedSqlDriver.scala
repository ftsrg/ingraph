package ingraph.compiler.sql.driver

import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSqlDriver extends BeforeAndAfterAll {
  this: Suite =>

  def driver() = new SqlDriver(translateCreateQueries = true)
  private val session: SqlSession = driver.session()
  
  def beginTransaction(): SqlTransaction = {
    session.beginTransaction()
  }

  override protected def afterAll(): Unit = {
    session.close()
    driver.close()
  }
}
