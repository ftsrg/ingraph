package ingraph.ire

import ingraph.ire.adapters.tuplecreators.TupleCreator
import ingraph.model.fplan.FNode

abstract class AbstractQueryAdapter extends AutoCloseable {

  val querySpecification: String
  val queryName: String
  val indexer: Indexer = new Indexer()

  val plan: FNode
  protected val tupleCreator: TupleCreator
  protected val engine: AnnotatedRelationalEngine

  override def close(): Unit = {
    engine.shutdown()
  }

}
