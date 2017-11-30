package ingraph.tests.runner

import ingraph.tests.LdbcSnbTestCase

abstract class TestRunner(tc: LdbcSnbTestCase) {
  def getResults(): Seq[Map[String, AnyRef]]
}
