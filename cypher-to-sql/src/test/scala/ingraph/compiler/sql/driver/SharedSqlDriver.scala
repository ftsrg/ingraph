package ingraph.compiler.sql.driver

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}

trait SharedSqlDriver extends BeforeAndAfterAll with BeforeAndAfterEach {
  this: Suite =>

  var driver: SqlDriver = _
  var session: SqlSession = _

  def initNewDriver(): SqlDriver = new SqlDriver(translateCreateQueries = true)

  private def init(): Unit = {
    driver = initNewDriver()
    session = driver.session()
  }

  override protected def beforeAll(): Unit = {
    super.beforeAll()

    init()
  }

  override protected def beforeEach(): Unit = {
    super.beforeEach()

    if (!session.isOpen)
      init()
  }

  override protected def afterEach(): Unit = {
    if (!session.isOpen)
      driver.close()

    super.afterEach()
  }

  override protected def afterAll(): Unit = {
    session.close()
    driver.close()

    super.afterAll()
  }
}
