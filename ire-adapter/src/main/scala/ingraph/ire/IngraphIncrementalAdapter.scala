package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import hu.bme.mit.ire.{Neo4jEntityToTupleMapper, Transaction, TransactionFactory}
import ingraph.compiler.JPlanParser

class IngraphIncrementalAdapter(
    override val querySpecification: String,
    override val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractIngraphAdapter {


  override val plan = JPlanParser.parse(querySpecification)
  override val engine = EngineFactory.createQueryEngine(plan, indexer)

  val transactionFactory = new TransactionFactory(16)
  transactionFactory.subscribe(engine.inputLookup)

  override val tupleMapper = new Neo4jEntityToTupleMapper(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap) with LongIdParser

  tupleMapper.transaction = transactionFactory.newBatchTransaction()
  indexer.subscribe(tupleMapper)


  def newTransaction(): Transaction = {
    val tran = transactionFactory.newBatchTransaction()
    tupleMapper.transaction = tran
    tran
  }

  def result(): Iterable[Tuple] = {
    tupleMapper.transaction.close()
    engine.getResults()
  }

  def addListener(listener: ChangeListener): Unit = {
    engine.addListener(listener)
  }

}
