package ingraph.sandbox

class RandomCompilationTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = None
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
}
