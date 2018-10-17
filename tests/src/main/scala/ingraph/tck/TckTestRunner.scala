package ingraph.tck

import java.io.File

import org.opencypher.tools.tck.api.{CypherTCK, Feature, Graph, Scenario}
import org.scalactic.source
import org.scalatest.FunSuiteLike

class TckScenarioSet(val selectedFeatures: Set[String] = Set(),
                     val ignoredScenarios: Set[String] = Set(),
                     val selectedScenarios: Set[String] = Set()) {
  val localFeaturePath = "/local-features"

  val localFeatures: Seq[Feature] = {
    Option(getClass.getResource(localFeaturePath))
      .map(url => new File(url.getFile))
      .flatMap(dir => if (dir.exists()) Some(dir) else None)
      .map(dir => CypherTCK.parseFilesystemFeatures(dir))
      .getOrElse(Seq())
  }

  def allScenarios: Seq[Scenario] = (TckScenarioSet.tckFeatures ++ localFeatures)
    .flatMap(_.scenarios)
    .groupBy(scenario => scenario.featureName -> scenario.name)
    .map(_._2.head)
    .toSeq
    .sortBy(scenario => scenario.featureName -> scenario.name)

  // scenarios have globally unique names
  assert(allScenarios.groupBy(_.name).forall(_._2.size == 1))

  val allScenariosInSelectedFeatures: Seq[Scenario] =
    if (selectedFeatures.isEmpty)
      allScenarios
    else
      allScenarios
        .filter(sc => selectedFeatures.contains(sc.featureName))

  val scenarios: Seq[Scenario] =
    (if (selectedScenarios.isEmpty)
      allScenariosInSelectedFeatures
    else
      allScenariosInSelectedFeatures
        .filter(sc => selectedScenarios.contains(sc.name))
      )
      .filterNot(sc => ignoredScenarios.contains(sc.name))

  val missingScenarios: Option[Set[String]] =
    if (selectedScenarios.isEmpty)
      None
    else
      Some(selectedScenarios -- allScenariosInSelectedFeatures.map(_.name))
}

object TckScenarioSet {
  val tckFeatures: Seq[Feature] = CypherTCK.parseClasspathFeatures("/features")

  val defaultSet: TckScenarioSet = new TckScenarioSet
}

trait TckTestRunner {
  this: FunSuiteLike =>

  def runTckTests(tckAdapterProvider: () => Graph,
                  selectedFeatures: Set[String] = Set(),
                  ignoredScenarios: Set[String] = Set(),
                  selectedScenarios: Set[String] = Set())
                 (implicit pos: source.Position): Unit = {
    val scenarioSet = new TckScenarioSet(selectedFeatures, ignoredScenarios, selectedScenarios)
    runTckTests(tckAdapterProvider, scenarioSet)(pos)
  }

  def runTckTests(tckAdapterProvider: () => Graph,
                  scenarioSet: TckScenarioSet)
                 (implicit pos: source.Position): Unit = {

    scenarioSet.missingScenarios.fold(/* no scenario is selected */) { missingScenarioNames =>
      test("All selected scenarios are found") {
        assert(missingScenarioNames.isEmpty)
      }(pos)
    }

    val coverageMap = scala.collection.mutable.HashMap.empty[String, (Int, Int)]

    scenarioSet.allScenariosInSelectedFeatures
      .foreach(scenario => {
        val testName = scenario.toString

        val isActive = scenarioSet.scenarios.contains(scenario)
        if (isActive)
          test(testName) {
            println(s"vvvvvvvvvvvvvvvv $testName vvvvvvvvvvvvvvvv")
            println()

            val graph = tckAdapterProvider()
            try {
              scenario(graph).execute()
            } finally {
              graph.close()
            }
          }(pos)
        else
          ignore(testName) {}(pos)

        val previousCoverageEntry = coverageMap.getOrElseUpdate(scenario.featureName, (0, 0))

        val activeCount = previousCoverageEntry._1
        val countOfAll = previousCoverageEntry._2
        val newCoverageEntry = ((
          if (isActive) activeCount + 1
          else activeCount)
          ->
          (countOfAll + 1)
          )

        coverageMap.put(scenario.featureName, newCoverageEntry)
      }
      )

    val sumOfActive = coverageMap.values.map(_._1).sum
    val sumOfScenariosInSelectedFeatures = coverageMap.values.map(_._2).sum
    val allScenariosCount = scenarioSet.allScenarios.size

    println(s"--------- $suiteName EXPECTED COVERAGE: $sumOfActive/$sumOfScenariosInSelectedFeatures ($allScenariosCount) ---------")
    println("featureName\tactive\tall")
    coverageMap.foreach { case (key, (active, all)) => println(s"$key\t$active\t$all") }
    println("-------------------------------------------------")
  }
}
