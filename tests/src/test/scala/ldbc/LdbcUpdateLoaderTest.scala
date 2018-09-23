package ldbc

import ingraph.ire.Indexer
import org.scalatest.FunSuite

class LdbcUpdateLoaderTest extends FunSuite {

  val CSV_DIR: String = "../graphs/ldbc-snb-bi/sf01/"

  ignore("parse streams") {
    val indexer = new Indexer()
    val loader = new LdbcUpdateLoader(indexer, CSV_DIR)
    loader.load()
  }

}
