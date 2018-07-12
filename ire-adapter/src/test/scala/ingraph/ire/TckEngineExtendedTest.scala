package ingraph.ire

import java.io.File

import org.opencypher.tools.tck.api.CypherTCK
import org.scalatest.FunSuite

class TckEngineExtendedTest extends FunSuite {

  val scenarios = (CypherTCK.parseFilesystemFeatures(new File(getClass.getResource("/local-features").getFile))
    ++ CypherTCK.parseClasspathFeatures("/features"))
    .flatMap(_.scenarios)
  val selectedFeatures = Set("MatchAcceptance", "MatchAcceptance2", "Local")
  val selectedScenarios = Set[String](
    "Use multiple MATCH clauses to do a Cartesian product"
    , "Filter out based on node prop name"
    , "Get integer property"
  )
  val ignoredScenarios = Set(
    "Variable length pattern checking labels on endnodes"
    , "Matching variable length patterns from a bound node"
    , "Variable length relationship in OPTIONAL MATCH"
  )

  def runTests() = {
    scenarios
      .filter(sc => selectedFeatures.contains(sc.featureName))
      .groupBy(_.toString).map(_._2.head)
      .foreach(sc => {
        val testName = sc.toString

        if ((selectedScenarios.contains(sc.name) || selectedScenarios.isEmpty) && !ignoredScenarios.contains(sc.name))
          test(testName) {
            println(testName)
            println()

            sc(new TckEngineAdapter).execute()
          }
        else
          ignore(testName) {}
      }
      )
  }

  runTests()
}
