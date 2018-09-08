package ingraph.ire

import ingraph.tck.TckTestRunner
import org.scalatest.FunSuite

class TckEngineExtendedTest extends FunSuite with TckTestRunner {

  val selectedFeatures = Set("MatchAcceptance", "MatchAcceptance2", "OptionalMatchAcceptance", "WithAcceptance", "Local")
  val selectedScenarios = Set[String](
    // original tests used in TckEngineTest
    "Aliasing",
    "Cope with shadowed variables",
    "Do not return non-existent nodes",
    "Do not return non-existent relationships",
    "Filter based on rel prop name",
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
    "Unnamed columns",

    // placeholder
    ""
  ).filter(!_.isEmpty)

  val ignoredScenarios = Set(
    // tests that causes the execution to hang
    "Variable length pattern checking labels on endnodes",
    "Matching variable length patterns from a bound node",
    "Variable length relationship in OPTIONAL MATCH",

    // placeholder
    ""
  )

  runTckTests(() => new TckEngineAdapter, selectedFeatures, ignoredScenarios, selectedScenarios)
}
