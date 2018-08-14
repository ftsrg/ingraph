package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import hu.bme.mit.ire.{DataSource, DataSourceFactory}
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.compiler.FPlanParser
import ingraph.ire.adapters.tuplecreators.TupleCreator
import org.supercsv.prefs.CsvPreference

class IncrementalQueryAdapter(
    override val querySpecification: String,
    override val queryName: String = "anonymous",
    override val indexer: Indexer = new Indexer()
  ) extends AbstractQueryAdapter {

  override val plan = FPlanParser.parse(querySpecification)
  override protected val engine = EngineFactory.createQueryEngine(plan, indexer)

  val dataSourceFactory = new DataSourceFactory
  dataSourceFactory.subscribe(engine.inputLookup)

  override val tupleCreator = new TupleCreator(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1 -> kv._2.toSet).toMap, LongIdParser)

  tupleCreator.dataSource = dataSourceFactory.newDataSource
  indexer.subscribe(tupleCreator)


  def newDataSource(): DataSource = {
    val dataSource = dataSourceFactory.newDataSource
    tupleCreator.dataSource = dataSource
    dataSource
  }

  def results(): Iterable[Tuple] = {
    tupleCreator.dataSource.close()
    engine.getResults()
  }

  def addListener(listener: ChangeListener): Unit = {
    engine.addListener(listener)
  }

  def readCsv2(vertexFileNames: Map[String, List[String]],
               edgeFilenames: Map[String, (String, String, String)],
               csvPreference: CsvPreference = CsvPreference.STANDARD_PREFERENCE) {
    import scala.collection.JavaConverters._
    // sorry :-)
    tupleCreator.dataSource = newDataSource()

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

}
