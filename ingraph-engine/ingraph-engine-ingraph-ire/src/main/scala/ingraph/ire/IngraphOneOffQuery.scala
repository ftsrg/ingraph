package ingraph.ire

import hu.bme.mit.ire.{Transaction, TransactionFactory}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.expressions.ExpressionUnwrapper
import ingraph.relalg2rete.Relalg2ReteTransformationAndSchemaCalculator
import org.supercsv.prefs.CsvPreference
import relalg.AttributeVariable

import scala.collection.JavaConverters._

class IngraphOneOffQuery(querySpecification: String, queryName: String, indexer: Indexer = new Indexer()) {
  private val reteCalc = new Relalg2ReteTransformationAndSchemaCalculator

  val plan = reteCalc.apply(Cypher2Relalg.processString(querySpecification, queryName))
  val engine = EngineFactory.createQueryEngine(plan.getRootExpression, indexer)
  val transactionFactory = new TransactionFactory(16)
  transactionFactory.subscribe(engine.inputLookup)

  private val tupleMapper = new EntityToTupleMapper(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap,
    engine.inputLookup) with LongIdParser
  tupleMapper.transaction = transactionFactory.newBatchTransaction()
  indexer.fill(tupleMapper)
  tupleMapper.transaction.close()

  def resultNames() : Vector[String] = {
    import ingraph.expressionparser.Conversions._
    plan.getRootExpression.getExternalSchema.map {
      case a: AttributeVariable => s"${ExpressionUnwrapper.extractBaseVariable(a).getName}.${a.getName}"
      case e => e.getName
    }
  }

  // Do this on initialization? Make this an object?
  def terminate(): Iterable[Tuple] = {
    tupleMapper.transaction.close()
    engine.getResults()
  }
}
