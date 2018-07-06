package ingraph.sandbox

import ingraph.compiler.test.{CompilerTest, CompilerTestConfig}

class TrainBenchmarkCompilationTest extends CompilerTest {

  override val config = CompilerTestConfig(querySuitePath = Some("trainbenchmark")
    , compileGPlanOnly = true
    , skipGPlanResolve = false
    , skipGPlanBeautify = false
    , printQuery = false
    , printCypher = true
    , printGPlan = true
    , printNPlan = false
    , printFPlan = false
  )

  test("PosLength.cypher") {
    compileFromFile("PosLength")
  }

  test("ConnectedSegments.cypher") {
    compileFromFile("ConnectedSegments")
  }

  test("RouteSensor.cypher") {
    compileFromFile("RouteSensor")
  }

  test("SwitchMonitored.cypher") {
    compileFromFile("SwitchMonitored")
  }

  test("SwitchSet.cypher") {
    compileFromFile("SwitchSet")
  }

  test("SemaphoreNeighbor.cypher") {
    compileFromFile("SemaphoreNeighbor")
  }

}
