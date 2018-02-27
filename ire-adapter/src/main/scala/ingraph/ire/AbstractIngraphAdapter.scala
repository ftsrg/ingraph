package ingraph.ire

import hu.bme.mit.ire.{Transaction, TupleCreator}
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.model.fplan.FNode
import org.supercsv.prefs.CsvPreference
import org.apache.commons.lang3.tuple.{ImmutableTriple, Triple => ATriple}

import scala.collection.JavaConverters._


abstract class AbstractIngraphAdapter {

  val querySpecification: String
  val queryName: String
  val indexer: Indexer = new Indexer()

  val plan: FNode
  val tupleMapper: TupleCreator
  val engine: AnnotatedRelationalEngine

  def readCsv(vertexFileName: Map[String, List[String]],
              edgeFilenames: Map[String, (String, String, String)],
              transaction: Transaction,
              csvPreference: CsvPreference = CsvPreference.STANDARD_PREFERENCE) {
    import scala.collection.JavaConverters._
    // sorry :-)
    tupleMapper.transaction = transaction
    val vertexFilenamesJava = vertexFileName.map(kv => kv._1 -> java.util.Arrays.asList(kv._2: _*))
      .asJava.asInstanceOf[java.util.Map[String, java.util.Collection[String]]]
    val loader = new MassCsvLoader(
      vertexFilenamesJava,
      edgeFilenames.map { case (k, v) => (k, ImmutableTriple.of(v._1, v._2, v._3)) }.asJava,
      csvPreference
    )
    for (node <- loader.getVertices.asScala) {
      indexer.addVertex(node)
    }
    for (relationship <- loader.getEdges.asScala) {
      indexer.addEdge(relationship)
    }
  }

}
