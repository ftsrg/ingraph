package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import org.opencypher.tools.tck.api.CypherTCK
import org.scalatest.FunSuite

class TckEngineExtendedTest extends FunSuite {

  val scenarios = CypherTCK.parseClasspathFeatures("/features").flatMap(_.scenarios)
  val selectedFeatures = Set("MatchAcceptance")
  val selectedScenarios = Set(
    "Use multiple MATCH clauses to do a Cartesian product"
    // TODO support node format
    //    , "Filter out based on node prop name"
  )

  def runTests() = {
    scenarios
      .filter(sc => selectedFeatures.contains(sc.featureName))
      .groupBy(_.toString).map(_._2.head)
      .foreach(sc => {
        val testName = sc.toString

        if (selectedScenarios.contains(sc.name))
          test(testName) {
            sc(new TckEngineAdapter).execute()
          }
        else
          ignore(testName) {}
      }
      )
  }

  runTests()
}
