package ldbc

import org.scalatest.FunSuite

class LdbcUpdateLoaderTest extends FunSuite {

  val csvDir: String = "../graphs/ldbc-snb-bi/sf01/"
  val updateQueryPrefix: String = "../queries/ldbc-snb-interactive/interactive-update-"
  val updateQueryPostfix: String = ".cypher"

  test("parse streams") {
    val loader = new LdbcUpdateLoader(csvDir, updateQueryPrefix, updateQueryPostfix)
    loader.generateQuerySpecifications()
  }

}
