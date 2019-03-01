package ingraph.compiler.sql.driver

import org.neo4j.driver.v1.{Driver, Session}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}

trait SharedDriver[DriverT <: Driver, SessionT <: Session] extends BeforeAndAfterAll with BeforeAndAfterEach {
  this: Suite =>

  var driver: DriverT = _
  var session: SessionT = _

  def initNewDriver(): DriverT

  protected def init(): Unit = {
    driver = initNewDriver()
    // any alternative?
    session = driver.session().asInstanceOf[SessionT]
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
