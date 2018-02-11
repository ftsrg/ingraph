package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.{Neo4jEntityToTupleMapper, TransactionFactory}
import ingraph.compiler.FPlanParser

class IngraphSearchAdapter(
    override val querySpecification: String,
    override val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractIngraphAdapter {

  override val plan = FPlanParser.parse(querySpecification)
  override val engine = EngineFactory.createQueryEngine(plan, indexer)

  val transactionFactory = new TransactionFactory(16)
  transactionFactory.subscribe(engine.inputLookup)

  override val tupleMapper = new Neo4jEntityToTupleMapper(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap) with LongIdParser

  tupleMapper.transaction = transactionFactory.newBatchTransaction()
  indexer.fill(tupleMapper)

  // Do this on initialization? Make this an object?
  def terminate(): Iterable[Tuple] = {
    tupleMapper.transaction.close()
    engine.getResults()
  }
}
