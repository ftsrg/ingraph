package ingraph.tck

import java.io.File

import org.opencypher.tools.tck.api.{CypherTCK, Feature, Graph, Scenario}
import org.scalatest.FunSuiteLike

trait TckTestRunner {
  this: FunSuiteLike =>

  val tckFeatures: Seq[Feature] = CypherTCK.parseClasspathFeatures("/features")

  def localFeaturePath = "/local-features"

  def localFeatures: Seq[Feature] = {
    Option(getClass.getResource(localFeaturePath))
      .map(url => new File(url.getFile))
      .flatMap(dir => if (dir.exists()) Some(dir) else None)
      .map(dir => CypherTCK.parseFilesystemFeatures(dir))
      .getOrElse(Seq())
  }

  def scenarios: Seq[Scenario] = (localFeatures ++ tckFeatures).flatMap(_.scenarios)

  def runTckTests(tckAdapterProvider: () => Graph, selectedFeatures: Set[String] = Set(), ignoredScenarios: Set[String] = Set(), selectedScenarios: Set[String] = Set()): Unit = {
    val scenariosInSelectedFeatures =
      if (selectedFeatures.isEmpty)
        scenarios
      else
        scenarios
          .filter(sc => selectedFeatures.contains(sc.featureName))

    val uniqueScenarios = scenariosInSelectedFeatures
      .groupBy(scenario => scenario.featureName -> scenario.name).map(_._2.head)

    if (scenariosInSelectedFeatures.nonEmpty) {
      val nonExistingFeatures = selectedScenarios --
        uniqueScenarios.map(_.name)

      test("All selected features are found") {
        assert(nonExistingFeatures.isEmpty)
      }
    }

    uniqueScenarios
      .foreach(scenario => {
        val testName = scenario.toString

        if ((selectedScenarios.isEmpty || selectedScenarios.contains(scenario.name)) && !ignoredScenarios.contains(scenario.name))
          test(testName) {
            println(testName)
            println()

            scenario(tckAdapterProvider()).execute()
          }
        else
          ignore(testName) {}
      }
      )
  }
}
