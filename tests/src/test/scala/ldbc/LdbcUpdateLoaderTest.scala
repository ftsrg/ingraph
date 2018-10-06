package ldbc

import org.scalatest.FunSuite

import scala.io.Source

class LdbcUpdateLoaderTest extends FunSuite {

  val CSV_DIR: String = "../graphs/ldbc-snb-bi/sf01/"
  val QUERY_PREFIX: String = "../queries/ldbc-snb-interactive/interactive-update-"
  val QUERY_POSTFIX: String = ".cypher"

  test("parse streams") {
    val loader = new LdbcUpdateLoader(CSV_DIR, QUERY_PREFIX, QUERY_POSTFIX)
    loader.load()
  }

}
