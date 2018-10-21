package ingraph.compiler.sql.driver

import ingraph.compiler.sql.driver.Neo4jTckTest.scenarioSet
import ingraph.driver.neo4j.Neo4jDriver
import ingraph.tck.{TckScenarioSet, TckTestRunner}
import org.neo4j.driver.v1.{Driver, Session}
import org.scalatest.FunSuite

class Neo4jTckTest extends FunSuite with TckTestRunner with SharedDriver[Driver, Session] {
  runTckTests(() => new TckAdapter(session), scenarioSet)

  override def initNewDriver(): Driver = new Neo4jDriver
}

object Neo4jTckTest {
  val selectedFeatures: Set[String] = Set(
    "Local",
    ""
  ).filter(!_.isEmpty)

  val selectedScenarios: Set[String] = Set(
    "OPTIONAL MATCH and WHERE",
    "OPTIONAL MATCH on two relationships and WHERE",
    "Two OPTIONAL MATCH clauses and WHERE",
    ""
  ).filter(!_.isEmpty)

  val ignoredScenarios: Set[String] = Set(
    ""
  ).filter(!_.isEmpty)

  val scenarioSet = new TckScenarioSet(selectedFeatures, ignoredScenarios, selectedScenarios)
}
