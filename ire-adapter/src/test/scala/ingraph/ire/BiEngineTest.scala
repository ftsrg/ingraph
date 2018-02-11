package ingraph.ire

class BiEngineTest extends EngineTest {

  override val queryDir: String = "ldbc-snb-bi"

  test("bi-101: Count persons") {
    run("""MATCH (p:Person)-[:IS_LOCATED_IN]->(c:country)
        |RETURN p, count(c) AS cc
      """.stripMargin)
  }

  // compiles
  ignore("bi-01 from file: Posting summary") {
    runFromFile("bi-01")
  }

  ignore("bi-02 from file: Top tags for country, age, gender, time") {
    runFromFile("bi-02")
  }

  ignore("bi-03 from file: Tag evolution") {
    runFromFile("bi-03")
  }

  test("bi-04 from file: Popular topics in a country") {
    runFromFile("bi-04")
  }

  ignore("bi-05 from file: Top posters in a country") {
    runFromFile("bi-05")
  }

  test("bi-06 from file: Most active Posters of a given Topic") {
    runFromFile("bi-06")
  }

  test("bi-07 from file: Most authoritative users on a given topic") {
    runFromFile("bi-07")
  }

  test("bi-08 from file: Related Topics") {
    runFromFile("bi-08")
  }

  // compiles
  ignore("bi-09 from file: Forum with related Tags") {
    runFromFile("bi-09")
  }

  ignore("bi-10 from file: Central Person for a Tag") {
    runFromFile("bi-10")
  }

  ignore("bi-11 from file: Unrelated replies") {
    runFromFile("bi-11")
  }

  test("bi-12 from file: Trending Posts") {
    runFromFile("bi-12")
  }

  ignore("bi-13 from file: Popular Tags per month in a country") {
    runFromFile("bi-13")
  }

  // compiles
  ignore("bi-14 from file: Top thread initiators") {
    runFromFile("bi-14")
  }

  // compiles
  ignore("bi-15 from file: Social normals") {
    runFromFile("bi-15")
  }

  ignore("bi-16 from file: Experts in social circle") {
    runFromFile("bi-16")
  }

  test("bi-17 from file: Friend triangles") {
    runFromFile("bi-17")
  }

  // compiles
  ignore("bi-18 from file: How many persons have a given number of posts") {
    runFromFile("bi-18")
  }

  // compiles
  ignore("bi-19 from file: Stranger's interaction") {
    runFromFile("bi-19")
  }

  // compiles
  ignore("bi-20 from file: High-level topics") {
    runFromFile("bi-20")
  }

  ignore("bi-21 from file: Zombies in a country") {
    runFromFile("bi-21")
  }

  ignore("bi-22 from file: International dialog") {
    runFromFile("bi-22")
  }

  test("bi-23 from file: Holiday destinations") {
    runFromFile("bi-23")
  }

  // compiles
  ignore("bi-24 from file: Messages by Topic and Continent") {
    runFromFile("bi-24")
  }

  ignore("bi-25 from file: Weighted interaction paths") {
    runFromFile("bi-25")
  }
}
