package ingraph.ire

import org.supercsv.prefs.CsvPreference
import hu.bme.mit.ire.Transaction
import hu.bme.mit.ire.TransactionFactory
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.expressions.ExpressionUnwrapper
import ingraph.relalg2rete.Relalg2ReteTransformationAndSchemaCalculator
import relalg.AttributeVariable
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener

import scala.collection.JavaConverters._

class IngraphAdapter(querySpecification: String, queryName: String) {
  private val reteCalc = new Relalg2ReteTransformationAndSchemaCalculator

  val plan = reteCalc.apply(Cypher2Relalg.processString(querySpecification, queryName))
  val engine = EngineFactory.createQueryEngine(plan.getRootExpression)

  private val tupleMapper = new EntityToTupleMapper(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap,
    engine.inputLookup) with LongIdParser
  private val indexer = new Indexer(tupleMapper)

  def readCsvJava(nodeFilenames: java.util.Map[String, java.util.List[String]],
                  relationshipFilenames: java.util.Map[String, String],
                  transaction: Transaction,
                  csvPreference: CsvPreference = CsvPreference.STANDARD_PREFERENCE) {
    readCsv(
      nodeFilenames.asScala.mapValues(_.asScala.toList).toMap,
      relationshipFilenames.asScala.toMap,
      transaction,
      csvPreference
    )
  }

  def readCsv(nodeFilenames: Map[String, List[String]],
              relationshipFilenames: Map[String, String],
              transaction: Transaction,
              csvPreference: CsvPreference = CsvPreference.STANDARD_PREFERENCE) {
    import scala.collection.JavaConverters._
    // sorry :-)
    tupleMapper.transaction = transaction
    val javaNodeFilenames = nodeFilenames.map(kv => kv._1 -> java.util.Arrays.asList(kv._2: _*))
      .asJava.asInstanceOf[java.util.Map[String, java.util.Collection[String]]]
    val loader = new MassCsvLoader(javaNodeFilenames, relationshipFilenames.asJava, csvPreference)
    for (node <- loader.getNodes.asScala) {
      indexer.addVertex(node)
    }
    for (relationship <- loader.getRelationships.asScala) {
      indexer.addEdge(relationship)
    }
  }

  def resultNames() : Vector[String] = {
    import ingraph.expressionparser.Conversions._
    plan.getRootExpression.getExternalSchema.map {
      case a: AttributeVariable => s"${ExpressionUnwrapper.extractBaseVariable(a).getName}.${a.getName}"
      case e => e.getName
    }
  }

  def newTransaction(): Transaction = {
    val tf = new TransactionFactory(16)
    tf.subscribe(engine.inputLookup)
    val tran = tf.newBatchTransaction()
    tran
  }

  def result(): Iterable[Tuple] = {
    engine.getResults()
  }

  def addListener(listener: ChangeListener): Unit = {
    engine.addListener(listener)
  }

}
