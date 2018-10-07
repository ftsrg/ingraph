package ingraph.ire

import ingraph.ire.datatypes.Tuple
import ingraph.ire.inputs.InputTransactionFactory
import ingraph.ire.adapters.tuplecreators.PullTupleCreator

class OneTimeQueryAdapter(
    val querySpecification: String,
    val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractQueryAdapter {

  val inputTransactionFactory = new InputTransactionFactory
  inputTransactionFactory.subscribe(engine.inputLookup)
  val inputTransaction = inputTransactionFactory.newInputTransaction
  new PullTupleCreator(
    engine.vertexConverters.values.flatten.toVector.distinct,
    engine.edgeConverters.values.flatten.toVector.distinct,
    indexer, inputTransaction, LongIdParser
  )

  override def results(): Iterable[Tuple] = {
    inputTransaction.sendAll()
    engine.getResults()
  }

  override def close() {
    engine.shutdown()
  }

}
