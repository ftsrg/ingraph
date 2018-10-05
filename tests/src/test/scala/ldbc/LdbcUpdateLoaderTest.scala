package ldbc

import ingraph.ire.Indexer
import org.scalatest.FunSuite

import scala.io.Source

class LdbcUpdateLoaderTest extends FunSuite {

  val CSV_DIR: String = "../graphs/ldbc-snb-bi/sf01/"
  val QUERY_PREFIX: String = "../queries/ldbc-snb-interactive/interactive-update-"
  val QUERY_POSTFIX: String = ".cypher"

  test("parse streams") {
    val indexer = new Indexer()
    val loader = new LdbcUpdateLoader(indexer, CSV_DIR)
    //loader.load()

    for (query <- 1 to 8) {
      val querySpecification = Source.fromFile(s"${QUERY_PREFIX}${query}${QUERY_POSTFIX}").getLines().mkString("\n")
      println(querySpecification)
    }
  }

}
