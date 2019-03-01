package ingraph.sandbox

import ingraph.compiler.test.{CompilerTest, CompilerTestConfig}

class InteractiveCompilerTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = Some("ldbc-snb-interactive")
    , compileGPlanOnly = false
    , skipGPlanResolve = false
    , skipGPlanBeautify = false
    , printQuery = true
    , printCypher = false
    , printGPlan = true
    , printNPlan = true
    , printFPlan = true
  )

  test("interactive-update-1 from file: Add Person") {
    val stages=compileFromFile("interactive-update-1")
  }

  test("interactive-update-2 from file: Add Post Like") {
    val stages=compileFromFile("interactive-update-2")
  }

  test("interactive-update-3 from file: Add Comment Like") {
    val stages=compileFromFile("interactive-update-3")
  }

  test("interactive-update-4 from file: Add Forum") {
    val stages=compileFromFile("interactive-update-4")
  }

  test("interactive-update-5 from file: Add Forum Membership") {
    val stages=compileFromFile("interactive-update-5")
  }

  test("interactive-update-6 from file: Add Post") {
    val stages=compileFromFile("interactive-update-6")
  }

  test("interactive-update-7 from file: Add Comment") {
    val stages=compileFromFile("interactive-update-7")
  }

  test("interactive-update-8 from file: Add Friendship") {
    val stages=compileFromFile("interactive-update-8")
  }

}
