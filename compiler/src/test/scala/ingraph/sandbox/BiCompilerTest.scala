package ingraph.sandbox

class BiCompilerTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = Some("ldbc-snb-bi")
    , compileQPlanOnly = false
    , skipQPlanResolve = false
    , skipQPlanBeautify = false
    , printQuery = true
    , printCypher = false
    , printQPlan = true
    , printJPlan = true
    , printFPlan = true
  )

  //FIXME: FPlanToTPlan
  ignore("bi-01 from file: Posting summary") {
    val stages=compileFromFile("bi-01")
  }

  ignore("bi-02 from file: Top tags for country, age, gender, time") {
    val stages=compileFromFile("bi-02")
  }

  ignore("bi-03 from file: Tag evolution") {
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

  //FIXME: FPlanToTPlan
  ignore("bi-09 from file: Forum with related Tags") {
    val stages=compileFromFile("bi-09")
  }

  ignore("bi-10 from file: Central Person for a Tag") {
    val stages=compileFromFile("bi-10")
  }

  ignore("bi-11 from file: Unrelated replies") {
    val stages=compileFromFile("bi-11")
  }

  test("bi-12 from file: Trending Posts") {
    val stages=compileFromFile("bi-12")
  }

  ignore("bi-13 from file: Popular Tags per month in a country") {
    val stages=compileFromFile("bi-13")
  }

  test("bi-14 from file: Top thread initiators") {
    val stages=compileFromFile("bi-14")
  }

  //FIXME: FPlanToTPlan
  ignore("bi-15 from file: Social normals") {
    val stages=compileFromFile("bi-15")
  }

  ignore("bi-16 from file: Experts in social circle") {
    val stages=compileFromFile("bi-16")
  }

  test("bi-17 from file: Friend triangles") {
    val stages=compileFromFile("bi-17")
  }

  test("bi-18 from file: How many persons have a given number of posts") {
    val stages=compileFromFile("bi-18")
  }

  //FIXME: FPlanToTPlan
  ignore("bi-19 from file: Stranger's interaction") {
    val stages=compileFromFile("bi-19")
  }

  test("bi-20 from file: High-level topics") {
    val stages=compileFromFile("bi-20")
  }

  ignore("bi-21 from file: Zombies in a country") {
    val stages=compileFromFile("bi-21")
  }

  ignore("bi-22 from file: International dialog") {
    val stages=compileFromFile("bi-22")
  }

  test("bi-23 from file: Holiday destinations") {
    val stages=compileFromFile("bi-23")
  }

  //FIXME: FPlanToTPlan
  ignore("bi-24 from file: Messages by Topic and Continent") {
    val stages=compileFromFile("bi-24")
  }

  ignore("bi-25 from file: Weighted paths") {
    val stages=compileFromFile("bi-25")
  }

}
