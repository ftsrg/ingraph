package ldbc

import org.scalatest.FunSuite

class LdbcUpdateLoaderTest extends FunSuite {

  val csvDir: String = "../graphs/ldbc-snb-bi/csv-sftiny/"
  val updateQueryPrefix: String = "../queries/ldbc-snb-interactive/interactive-update-"
  val updateQueryPostfix: String = ".cypher"

  test("Generate update query specifications") {
    val loader = new LdbcUpdateLoader(csvDir, updateQueryPrefix, updateQueryPostfix)
    loader.generateQuerySpecifications()
  }

}
