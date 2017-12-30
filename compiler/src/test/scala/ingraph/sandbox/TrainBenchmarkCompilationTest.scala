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

  test("Random test from cypher string") {
    compile(
      """MATCH (segment:Segment)
        |WHERE NOT NOT NOT (segment.length > 0)
        |RETURN segment, segment.length AS length
        |     , case segment.length when +0 then 'zero' when --+-1 then 'almost OK' else 'too bad' end AS lengthComment""".stripMargin)

  }

  test("Random double edge variable in the same MATCH") {
    compile(
      """MATCH
        |  (a)-[e]->(b),
        |  (a)-[e]->(b)
        |RETURN a, e, b""".stripMargin)

  }

  test("Random double edge variable in separate MATCHes") {
    compile(
      """MATCH (a)-[e]->(b)
        |MATCH (a)-[e]->(b)
        |RETURN a, e, b""".stripMargin)

  }

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
