package ingraph.ire

import org.scalatest.FunSuite

class EngineTest extends FunSuite {

  def run(readQuery: String): Unit = {
    val indexer = new Indexer()
    val readAdapter = new IngraphIncrementalAdapter(readQuery, "read", indexer)
    val result = readAdapter.result()
  }

}
