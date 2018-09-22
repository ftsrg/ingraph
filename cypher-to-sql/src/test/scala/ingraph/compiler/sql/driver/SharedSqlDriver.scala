package ingraph.compiler.sql.driver

import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSqlDriver extends BeforeAndAfterAll {
  this: Suite =>

  def translateCreateQueries: Boolean

  private val driver = new SqlDriver(translateCreateQueries)
  private val session: SqlSession = driver.session()
  
  def beginTransaction(): SqlTransaction = {
    session.beginTransaction()
  }

  override protected def afterAll(): Unit = {
    session.close()
    driver.close()
  }
}
