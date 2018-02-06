package ingraph.ire

import org.scalatest.FunSuite

class ZenaptixTest extends FunSuite {
  val indexer = new Indexer()
  val adapter = new IngraphIncrementalAdapter(querySpecification, queryName, indexer)

  val tran = adapter.newTransaction()
  adapter.readCsv(nodeFilenames, relationshipFilenames, tran)
  tran.close()
}
