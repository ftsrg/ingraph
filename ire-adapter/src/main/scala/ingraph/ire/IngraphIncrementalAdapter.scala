package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import hu.bme.mit.ire.{TupleCreator, Transaction, TransactionFactory}
import ingraph.compiler.FPlanParser

class IngraphIncrementalAdapter(
    override val querySpecification: String,
    override val queryName: String = "anonymous",
    override val indexer: Indexer = new Indexer()
  ) extends AbstractIngraphAdapter {


  override val plan = FPlanParser.parse(querySpecification)
  override val engine = EngineFactory.createQueryEngine(plan, indexer)

  val transactionFactory = new TransactionFactory(16)
  transactionFactory.subscribe(engine.inputLookup)

  override val tupleCreator = new TupleCreator(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap, LongIdParser)

  tupleCreator.transaction = transactionFactory.newBatchTransaction()
  indexer.subscribe(tupleCreator)


  def newTransaction(): Transaction = {
    val tran = transactionFactory.newBatchTransaction()
    tupleCreator.transaction = tran
    tran
  }

  def result(): Iterable[Tuple] = {
    tupleCreator.transaction.close()
    engine.getResults()
  }

  def addListener(listener: ChangeListener): Unit = {
    engine.addListener(listener)
  }



}
