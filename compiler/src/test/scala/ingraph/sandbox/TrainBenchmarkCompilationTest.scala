package ingraph.sandbox

class TrainBenchmarkCompilationTest extends CompilerTest {

  override val config = CompilerTestConfig(querySuitePath = Some("trainbenchmark")
    , compileQPlanOnly = true
    , skipQPlanResolve = false
    , skipQPlanBeautify = false
    , printQuery = false
    , printCypher = true
    , printQPlan = true
    , printJPlan = false
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
