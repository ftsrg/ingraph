package ingraph.nre

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import hu.bme.mit.ire.{Neo4jEntityToTupleMapper, Transaction, TransactionFactory}
import ingraph.compiler.IPlanParser
import ingraph.ire.{AbstractIngraphAdapter, AnnotatedRelationalEngine, Indexer, LongIdParser}
import ingraph.model.eplan.ENode

class IngraphNaiveAdapter(
    override val querySpecification: String,
    override val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractIngraphAdapter {


  val plan: ENode = IPlanParser.parse(querySpecification)
  override val engine: AnnotatedRelationalEngine = EngineFactory.createQueryEngine(plan, indexer)

  val transactionFactory = new TransactionFactory(16)
  transactionFactory.subscribe(engine.inputLookup)

  override val tupleMapper = new Neo4jEntityToTupleMapper(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap,
    engine.inputLookup) with LongIdParser

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
