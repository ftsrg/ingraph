package ingraph.ire

import hu.bme.mit.ire.{DataSource, TupleCreator}
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.model.fplan.FNode
import org.supercsv.prefs.CsvPreference

abstract class AbstractIngraphAdapter extends AutoCloseable {

  val querySpecification: String
  val queryName: String
  val indexer: Indexer = new Indexer()

  val plan: FNode
  val tupleCreator: TupleCreator
  val engine: AnnotatedRelationalEngine

  def readCsv(vertexFileNames: Map[String, List[String]],
              edgeFilenames: Map[String, (String, String, String)],
              dataSource: DataSource,
              csvPreference: CsvPreference = CsvPreference.STANDARD_PREFERENCE) {
    import scala.collection.JavaConverters._
    // sorry :-)
    tupleCreator.dataSource = dataSource

    val loader = new MassCsvLoader(csvPreference)

    for (vertex <- vertexFileNames) {
      val fileName = vertex._1
      val labels = vertex._2
      for (csvVertex <- loader.loadVertices(fileName).asScala) {
        indexer.addVertex(csvVertex, labels)
      }
    }
    for (edge <- edgeFilenames) {
      val fileName = edge._1
      val sourceVertexLabel: String = edge._2._1
      val edgeType         : String = edge._2._2
      val targetVertexLabel: String = edge._2._3
      for (csvEdge <- loader.loadEdges(fileName).asScala) {
        indexer.addEdge(csvEdge, sourceVertexLabel, edgeType, targetVertexLabel)
      }
    }
  }

  override def close(): Unit = {
    engine.shutdown()
  }

}
