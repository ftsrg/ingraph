package ingraph.compiler.sql.driver

import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSqlDriver extends BeforeAndAfterAll {
  this: Suite =>

  var driver: SqlDriver = _
  var session: SqlSession = _

  def initNewDriver(): SqlDriver = new SqlDriver(translateCreateQueries = true)

  override protected def beforeAll(): Unit = {
    super.beforeAll()

    driver = initNewDriver()
    session = driver.session()
  }

  override protected def afterAll(): Unit = {
    session.close()
    driver.close()

    super.afterAll()
  }
}
