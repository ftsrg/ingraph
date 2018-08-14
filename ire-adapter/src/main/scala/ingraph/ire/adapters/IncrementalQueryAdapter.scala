package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.inputs.InputMultiplexerFactory
import hu.bme.mit.ire.listeners.ChangeListener
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.csv.EdgeMetaData
import ingraph.ire.adapters.tuplecreators.TupleCreator
import org.supercsv.prefs.CsvPreference

class IncrementalQueryAdapter(
    override val querySpecification: String,
    override val queryName: String = "anonymous",
    override val indexer: Indexer = new Indexer()
  ) extends AbstractQueryAdapter {

  val inputMultiplexerFactory = new InputMultiplexerFactory
  inputMultiplexerFactory.subscribe(engine.inputLookup)

  val tupleCreator = new TupleCreator(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap,
    LongIdParser,
    inputMultiplexerFactory.newInputMultiplexer()
  )
  indexer.subscribe(tupleCreator)

  override def results(): Iterable[Tuple] = {
    tupleCreator.inputMultiplexer.sendAll()
    engine.getResults()
  }

  def addListener(listener: ChangeListener): Unit = {
    engine.addListener(listener)
  }

  def readCsv(vertexFileNames: Map[String, List[String]],
              edgeFilenames: Map[String, EdgeMetaData],
              csvPreference: CsvPreference) {
    import scala.collection.JavaConverters._

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
      val sourceVertexLabel: String = edge._2.sourceVertexLabel
      val edgeType         : String = edge._2.edgeType
      val targetVertexLabel: String = edge._2.targetVertexLabel
      for (csvEdge <- loader.loadEdges(fileName).asScala) {
        indexer.addEdge(csvEdge, sourceVertexLabel, edgeType, targetVertexLabel)
      }
    }
  }

}