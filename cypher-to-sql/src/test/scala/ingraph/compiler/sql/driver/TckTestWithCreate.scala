package ingraph.compiler.sql.driver

import ingraph.compiler.sql.driver.TckTestWithCreate.scenarioSet
import ingraph.tck.{TckScenarioSet, TckTestRunner}
import org.scalatest.FunSuite

class TckTestWithCreate extends FunSuite with TckTestRunner with SharedSqlDriver {
  runTckTests(() => new TckAdapter(beginTransaction()), scenarioSet)
}

object TckTestWithCreate {
  val selectedFeatures: Set[String] = TckTest.scenarioSet.selectedFeatures
  val selectedScenarios: Set[String] = TckTest.scenarioSet.selectedScenarios

  val ignoredScenarios: Set[String] = Set(
    "Many CREATE clauses",
    "Returning multiple node property values",
    // placeholder
    ""
  ).filter(!_.isEmpty)

  val scenarioSet = new TckScenarioSet(selectedFeatures, ignoredScenarios, selectedScenarios)
}
