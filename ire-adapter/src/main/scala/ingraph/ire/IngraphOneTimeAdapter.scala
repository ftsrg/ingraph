package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.{PullTupleCreator, TransactionFactory, TupleCreator}
import ingraph.compiler.FPlanParser

class IngraphOneTimeAdapter (
    val querySpecification: String,
    val queryName: String,
    val indexer: Indexer = new Indexer()
  ) extends AutoCloseable {

  val plan = FPlanParser.parse(querySpecification)
  val engine = EngineFactory.createQueryEngine(plan, indexer)

  val transactionFactory = new TransactionFactory
  transactionFactory.subscribe(engine.inputLookup)
  val transaction = transactionFactory.newBatchTransaction()
  new PullTupleCreator(
    engine.vertexConverters.values.flatten.toVector.distinct,
    engine.edgeConverters.values.flatten.toVector.distinct,
    indexer, transaction, LongIdParser)

  /**
    * Terminate execution and return results
    */
  def terminate(): Iterable[Tuple] = {
    transaction.close()
    engine.getResults()
  }

  override def close() {
    engine.shutdown()
  }

}
