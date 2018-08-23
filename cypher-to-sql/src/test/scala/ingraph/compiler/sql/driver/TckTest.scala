package ingraph.compiler.sql.driver

import ingraph.tck.TckTestRunner
import org.scalatest.FunSuite

class TckTest extends FunSuite with TckTestRunner {

  val selectedFeatures = Set("MatchAcceptance")
  val selectedScenarios = Set(
    "Use multiple MATCH clauses to do a Cartesian product",

    // placeholder
    ""
  ).filter(!_.isEmpty)

  runTckTests(() => new TckAdapter, selectedFeatures, Set(), selectedScenarios)
}
