package ingraph.ire

import hu.bme.mit.ire.{Transaction, TupleCreator}
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.model.fplan.FNode
import org.apache.commons.lang3.tuple.ImmutableTriple
import org.supercsv.prefs.CsvPreference

abstract class AbstractIngraphAdapter {

  val querySpecification: String
  val queryName: String
  val indexer: Indexer = new Indexer()

  val plan: FNode
  val tupleCreator: TupleCreator
  val engine: AnnotatedRelationalEngine

  def readCsv(vertexFileName: Map[String, List[String]],
              edgeFilenames: Map[String, (String, String, String)],
              transaction: Transaction,
              csvPreference: CsvPreference = CsvPreference.STANDARD_PREFERENCE) {
    import scala.collection.JavaConverters._
    // sorry :-)
    tupleCreator.transaction = transaction

    val vertexFilenamesJava = vertexFileName.map(kv => kv._1 -> java.util.Arrays.asList(kv._2: _*))
      .asJava.asInstanceOf[java.util.Map[String, java.util.Collection[String]]]
    val edgeFilenamesJava = edgeFilenames.map { case (k, v) => (k, ImmutableTriple.of(v._1, v._2, v._3)) }.asJava

    val loader = new MassCsvLoader(vertexFilenamesJava, edgeFilenamesJava, csvPreference)
    for (node <- loader.getVertices.asScala) {
      indexer.addVertex(node)
    }
    for (relationship <- loader.getEdges.asScala) {
      indexer.addEdge(relationship)
    }
  }

}
