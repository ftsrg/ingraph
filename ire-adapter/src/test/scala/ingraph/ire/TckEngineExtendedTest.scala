package ingraph.ire

import java.io.File

import org.opencypher.tools.tck.api.CypherTCK
import org.scalatest.FunSuite

class TckEngineExtendedTest extends FunSuite {

  val scenarios = (CypherTCK.parseFilesystemFeatures(new File(getClass.getResource("/local-features").getFile))
    ++ CypherTCK.parseClasspathFeatures("/features"))
    .flatMap(_.scenarios)
  val selectedFeatures = Set("MatchAcceptance", "MatchAcceptance2", "OptionalMatchAcceptance", "WithAcceptance", "Local")
  val selectedScenarios = Set[String](
    // original tests used in TckEngineTest
    "Aliasing",
    "Cope with shadowed variables",
    "Do not return non-existent nodes",
    "Do not return non-existent relationships",
    "Filter out based on node prop name",
    "Get neighbours",
    "Get two related nodes",
    "Handle OR in the WHERE clause",
    //    "Handle comparison between node properties",
    "No dependencies between the query parts",
    //    "Respect predicates on the OPTIONAL MATCH",
    //    "Return two subgraphs with bound undirected relationship",
    //    "Returning bound nodes that are not part of the pattern",
    //    "Simple variable length pattern",
    //    "Three bound nodes pointing to the same node",
    //    "Two bound nodes pointing to the same node",
    "Use multiple MATCH clauses to do a Cartesian product",
    "WHERE after WITH should filter results",

    // additional tests
    //    "`collect()` filtering nulls",
    //    "Return relationships by collecting them as a list - undirected, starting from two extremes",

    // local tests
    "Get integer property",

    // placeholder
    ""
  ).filter(!_.isEmpty)

  val ignoredScenarios = Set(
    // https://github.com/FTSRG/ingraph/issues/342
    "Filter based on rel prop name",

    // tests that causes the execution to hang
    "Variable length pattern checking labels on endnodes",
    "Matching variable length patterns from a bound node",
    "Variable length relationship in OPTIONAL MATCH",

    // placeholder
    ""
  )

  def runTests() = {
    val nonExistingFeatures = selectedScenarios --
      scenarios
        .filter(sc => selectedFeatures.contains(sc.featureName))
        .groupBy(_.toString).map(_._2.head.name)

    test("All selected features are found") {
      assert(nonExistingFeatures.isEmpty)
    }

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
