package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import hu.bme.mit.ire.{DataSource, DataSourceFactory}
import ingraph.compiler.FPlanParser
import ingraph.ire.adapters.tuplecreators.TupleCreator

class IngraphIncrementalAdapter(
    override val querySpecification: String,
    override val queryName: String = "anonymous",
    override val indexer: Indexer = new Indexer()
  ) extends AbstractIngraphAdapter {


  override val plan = FPlanParser.parse(querySpecification)
  override val engine = EngineFactory.createQueryEngine(plan, indexer)

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

  def result(): Iterable[Tuple] = {
    tupleCreator.dataSource.close()
    engine.getResults()
  }

  def addListener(listener: ChangeListener): Unit = {
    engine.addListener(listener)
  }

}
