package ingraph.ire

import hu.bme.mit.ire.{Neo4jEntityToTupleMapper, Transaction}
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.model.fplan.FNode
import org.supercsv.prefs.CsvPreference

import scala.collection.JavaConverters._


abstract class AbstractIngraphAdapter {

  val querySpecification: String
  val queryName: String
  val indexer: Indexer = new Indexer()

  val plan: FNode
  val tupleMapper: Neo4jEntityToTupleMapper
  val engine: AnnotatedRelationalEngine
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

  def resultNames() : Seq[String] = {
    plan.internalSchema.map(_.toString())
  }

}
