package ingraph.compiler.sql.driver

import ingraph.compiler.sql.driver.TckTestWithCreate.scenarioSet
import ingraph.tck.{TckScenarioSet, TckTestRunner}
import org.scalatest.FunSuite

class TckTestWithCreate extends FunSuite with TckTestRunner {
  runTckTests(() => new TckAdapter(new SqlDriver(true)), scenarioSet)
}

object TckTestWithCreate {
  val selectedFeatures = Set("MatchAcceptance", "MatchAcceptance2", "Local")
  val selectedScenarios: Set[String] = Set(
    //    "Dependant CREATE with single row",
    //    "Dependant CREATE with single row - with aliased attribute",
    "Use multiple MATCH clauses to do a Cartesian product",

    // placeholder
    ""
  ).filter(!_.isEmpty)

  val scenarioSet = new TckScenarioSet(selectedFeatures, ignoredScenarios = Set(), selectedScenarios = selectedScenarios)
}
