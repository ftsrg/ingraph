package ingraph.sre

import ingraph.sre.SearchPlanFixtures._
import ingraph.sre.plan._
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SearchPlanExecutorTest extends FreeSpec {


  "Should return expected results #1" in {

    GetVerticesByLabelsOperationBinding.create(v(1), c("Person"))

  }

}
