package ingraph.compiler.sql.driver

import ingraph.tck.TckTestRunner
import org.scalatest.FunSuite

class TckTest extends FunSuite with TckTestRunner {

  val selectedFeatures = Set("MatchAcceptance", "Local")
  val selectedScenarios = Set(
    "Handle comparison between node properties",
    "Walk alternative relationships",
    "Return vertices and edges",

    // placeholder
    ""
  ).filter(!_.isEmpty)

  runTckTests(() => new TckAdapter, selectedFeatures, Set(), selectedScenarios)
}
