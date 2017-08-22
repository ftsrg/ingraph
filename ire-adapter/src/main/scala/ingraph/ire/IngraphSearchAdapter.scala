package ingraph.ire

import hu.bme.mit.ire.{Transaction, TransactionFactory}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.expressions.ExpressionUnwrapper
import ingraph.search2rete.Search2ReteTransformationAndSchemaCalculator
import org.supercsv.prefs.CsvPreference
import relalg.AttributeVariable

import scala.collection.JavaConverters._

class IngraphSearchAdapter(
    override val querySpecification: String,
    override val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractIngraphAdapter {

  val reteCalc = new Search2ReteTransformationAndSchemaCalculator
  override val plan = reteCalc.apply(Cypher2Relalg.processString(querySpecification, queryName))
  override val engine = EngineFactory.createQueryEngine(plan.getRootExpression, indexer)

  val transactionFactory = new TransactionFactory(16)
  transactionFactory.subscribe(engine.inputLookup)

  override val tupleMapper = new EntityToTupleMapper(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap,
    engine.inputLookup) with LongIdParser
  tupleMapper.transaction = transactionFactory.newBatchTransaction()
  indexer.fill(tupleMapper)

  // Do this on initialization? Make this an object?
  def terminate(): Iterable[Tuple] = {
    tupleMapper.transaction.close()
    engine.getResults()
  }
}
