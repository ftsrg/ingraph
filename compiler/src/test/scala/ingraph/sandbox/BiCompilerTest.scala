package ingraph.sandbox

import ingraph.compiler.test.{CompilerTest, CompilerTestConfig}

class BiCompilerTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = Some("ldbc-snb-bi")
    , compileGPlanOnly = false
    , skipGPlanResolve = false
    , skipGPlanBeautify = false
    , printQuery = true
    , printCypher = false
    , printGPlan = true
    , printNPlan = true
    , printFPlan = true
  )

  test("bi-01 from file: Posting summary") {
    val stages=compileFromFile("bi-01")
  }

  test("bi-02 from file: Top tags for country, age, gender, time") {
    val stages=compileFromFile("bi-02")
  }

  test("bi-03 from file: Tag evolution") {
    val stages=compileFromFile("bi-03")
  }

  test("bi-04 from file: Popular topics in a country") {
    val stages=compileFromFile("bi-04")
  }

  ignore("bi-05 from file: Top posters in a country") {
    val stages=compileFromFile("bi-05")
  }

  test("bi-06 from file: Most active Posters of a given Topic") {
    val stages=compileFromFile("bi-06")
  }

  test("bi-07 from file: Most authoritative users on a given topic") {
    val stages=compileFromFile("bi-07")
  }

  test("bi-08 from file: Related Topics") {
    val stages=compileFromFile("bi-08")
  }

  test("bi-09 from file: Forum with related Tags") {
    val stages=compileFromFile("bi-09")
  }

  test("bi-10 from file: Central Person for a Tag") {
    val stages=compileFromFile("bi-10")
  }

  // list comprehension
  ignore("bi-11 from file: Unrelated replies") {
    val stages=compileFromFile("bi-11")
  }

  test("bi-12 from file: Trending Posts") {
    val stages=compileFromFile("bi-12")
  }

  // list comprehension
  ignore("bi-13 from file: Popular Tags per month in a country") {
    val stages=compileFromFile("bi-13")
  }

  test("bi-14 from file: Top thread initiators") {
    val stages=compileFromFile("bi-14")
  }

  test("bi-15 from file: Social normals") {
    val stages=compileFromFile("bi-15")
  }

  // will not parse because of the $minPathDistance/$maxPathDistance variables
  ignore("bi-16 from file: Experts in social circle") {
    val stages=compileFromFile("bi-16")
  }

  test("bi-17 from file: Friend triangles") {
    val stages=compileFromFile("bi-17")
  }

  test("bi-18 from file: How many persons have a given number of posts") {
    val stages=compileFromFile("bi-18")
  }

  test("bi-19 from file: Stranger's interaction") {
    val stages=compileFromFile("bi-19")
  }

  test("bi-20 from file: High-level topics") {
    val stages=compileFromFile("bi-20")
  }

  ignore("bi-21 from file: Zombies in a country") {
    val stages=compileFromFile("bi-21")
  }

  // map literal
  ignore("bi-22 from file: International dialog") {
    val stages=compileFromFile("bi-22")
  }

  test("bi-23 from file: Holiday destinations") {
    val stages=compileFromFile("bi-23")
  }

  test("bi-24 from file: Messages by Topic and Continent") {
    val stages=compileFromFile("bi-24")
  }

  // list comprehension
  ignore("bi-25 from file: Weighted interaction paths") {
    val stages=compileFromFile("bi-25")
  }
}
